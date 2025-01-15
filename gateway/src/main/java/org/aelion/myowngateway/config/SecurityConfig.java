package org.aelion.myowngateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] publicRoutes = {
            "/api/v1/auth/**"
    };
    private final String jwtSecret = "h3j52B75jBV5Jhvk5b5K7HBk4hB12H5K8bBK8b8hjB2H4JbBK1J9b3KBH4JV5HV2hjv8hgcf7d8xwf1gkl5ioLK";

    @Bean
    @Primary
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        System.out.println("test1");
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(publicRoutes).permitAll()
                        .anyExchange().authenticated()
                )
                .addFilterAt(jwtAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public AuthenticationWebFilter jwtAuthenticationFilter() {
        System.out.println("test2");
        ReactiveAuthenticationManager authManager = authentication -> {
            String token = (String) authentication.getCredentials();
            System.out.println("Received token: " + token);

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                System.out.println("Token claims: " + claims);
                System.out.println("Subject: " + claims.getSubject());
                System.out.println("Role: " + claims.get("role", String.class));

                String role = claims.get("role", String.class);
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + role)
                );

                return Mono.just(new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        authorities
                ));
            } catch (Exception e) {
                System.err.println("Token validation error: " + e.getMessage());
                e.printStackTrace();
                return Mono.error(new BadCredentialsException("Invalid JWT token"));
            }
        };

        AuthenticationWebFilter filter = new AuthenticationWebFilter(authManager);

        filter.setServerAuthenticationConverter(exchange -> {
            String path = exchange.getRequest().getURI().getPath();

            if (isPublicRoute(path)) {
                return Mono.empty();
            }
            System.out.println("test4");


            String authorization = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authorization != null && authorization.startsWith("Bearer ")) {
                return Mono.just(new PreAuthenticatedAuthenticationToken(null, authorization.substring(7)));
            }
            return Mono.empty();
        });

        return filter;
    }

    @Bean
    public GlobalFilter jwtPropagationFilter() {
        System.out.println("test5");
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            if (isPublicRoute(path)) {
                return chain.filter(exchange);
            }
            System.out.println("test6");

            ServerHttpRequest request = exchange.getRequest();
            return ReactiveSecurityContextHolder.getContext()
                    .map(SecurityContext::getAuthentication)
                    .map(authentication -> {
                        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                        if (token != null && token.startsWith("Bearer ")) {
                            try {
                                Claims claims = Jwts.parserBuilder()
                                        .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                                        .build()
                                        .parseClaimsJws(token.substring(7))
                                        .getBody();

                                ServerHttpRequest mutatedRequest = request.mutate()
                                        .header("X-User-Id", claims.getSubject())
                                        .header("X-User-Role", claims.get("role", String.class))
                                        .build();
                                return exchange.mutate().request(mutatedRequest).build();
                            } catch (Exception e) {
                                return exchange;
                            }
                        }
                        return exchange;
                    })
                    .defaultIfEmpty(exchange)
                    .flatMap(chain::filter);
        };
    }

    public boolean isPublicRoute(String path) {
        return Arrays.stream(publicRoutes)
                .anyMatch(route ->
                        route.endsWith("**")
                                ? path.startsWith(route.substring(0, route.length() - 2))
                                : path.equals(route)
                );
    }
}

