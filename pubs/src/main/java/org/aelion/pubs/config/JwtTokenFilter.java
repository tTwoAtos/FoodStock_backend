package org.aelion.pubs.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${keyStore.path}")
    private String keyStorePath;

    @Value("${keyStore.password}")
    private String keyStorePassword;

    @Value("${keyStore.alias}")
    private String keyStoreAlias;

    @Bean
    public KeyStore getKeyStore() throws IOException, CertificateException, NoSuchAlgorithmException {

        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("JKS");
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
        try(InputStream keyStoreStream = getClass().getClassLoader().getResourceAsStream(keyStorePath)){
            if (keyStoreStream == null){
                throw new RuntimeException("Keystore file not found");
            }
            keyStore.load(keyStoreStream, keyStorePassword.toCharArray());
        }
        return keyStore;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                PublicKey publicKey = getKeyStore().getCertificate(keyStoreAlias).getPublicKey();

                Claims claims = Jwts.parserBuilder().setSigningKey(publicKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                List<String> roles = List.of(claims.get("role").toString());
                List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

                Long userId = claims.get("user_id", Long.class);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, authorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request, response);
    }
}
