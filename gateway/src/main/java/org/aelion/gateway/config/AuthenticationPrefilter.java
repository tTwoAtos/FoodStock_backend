package org.aelion.gateway.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class AuthenticationPrefilter extends AbstractGatewayFilterFactory<AuthenticationPrefilter.Config> {
    @Value("${keyStore.path}")
    private String keyStorePath;

    @Value("${keyStore.password}")
    private String keyStorePassword;

    @Value("${keyStore.alias}")
    private String alias;

    // exclude path /auth
    private List<String> publicUrls = Arrays.asList("api/v1/auth/**", "**/v3/api-docs");

    @Bean
    @Primary
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

    private PublicKey getPublicKey() throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("JKS");

        try {
            InputStream inputStream = new FileInputStream(keyStorePath);
            keyStore.load(inputStream, keyStorePassword.toCharArray());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        Certificate certificate = keyStore.getCertificate(alias);
        return certificate.getPublicKey();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().value();
            HttpHeaders httpHeaders = request.getHeaders();
            String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);

            if (isExcluded(path)) {
                return chain.filter(exchange);
            }

            if (token == null || !token.startsWith("Bearer ")) {
                return handleAuthError(exchange, "Missing or invalid token", HttpStatus.UNAUTHORIZED);
            }

            token = token.substring(7);

            try {
                PublicKey publicKey = getPublicKey();
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(publicKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String email = claims.getSubject();
                String role = claims.get("role").toString();

                exchange.getRequest().mutate()
                        .header("email", email)
                        .header("role", role)
                        .build();

                return chain.filter(exchange);
            } catch (Exception e) {
                return handleAuthError(exchange, "Invalid token", HttpStatus.FORBIDDEN);
            }
        };
    }

    private boolean isExcluded(String path) {
        Predicate<String> isExcluded = pattern -> path.contains(pattern.replace("**", ""));
        return publicUrls.stream().anyMatch(isExcluded);
    }

    private Mono<Void> handleAuthError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        responseBody.put("message", message);
        responseBody.put("status", status.value());
        responseBody.put("errorCode", status.value());

        try {
            byte[] bytes = objectMapper().writeValueAsBytes(responseBody);
            DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(dataBuffer));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ObjectMapper objectMapper() {
        JsonFactory factory = new JsonFactory();
        factory.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        ObjectMapper objectMapper = new ObjectMapper(factory);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss"));

        return objectMapper;
    }

    public static class Config {
        private List<String> excludedPattern;

        public List<String> getExcludedPattern() {
            return excludedPattern;
        }

        public void setExcludedPattern(List<String> excludedPattern) {
            this.excludedPattern = excludedPattern;
        }
    }
}
