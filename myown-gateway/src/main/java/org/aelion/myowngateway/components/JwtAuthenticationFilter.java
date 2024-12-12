//package org.aelion.myowngateway.components;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//import javax.crypto.SecretKey;
//import java.util.List;
//
//@Component
//@Order(1)
//public class JwtAuthenticationFilter implements WebFilter {
//
//    private final SecretKey secretKey;
//    private final String rolesClaim;
//
//    public JwtAuthenticationFilter(@Value("${jwt.secret}") String secret,
//                                   @Value("${jwt.roles-claim}") String rolesClaim) {
//        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
//        this.rolesClaim = rolesClaim;
//    }
//
//    @Bean
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            Claims claims = validateToken(token);
//
//            if (claims != null) {
//                // Ajoute les claims dans les headers pour les microservices en aval
//                ServerHttpRequest request = exchange.getRequest().mutate()
//                        .header("X-Username", claims.getSubject())
//                        .header("X-Roles", String.join(",", (List<String>) claims.get(rolesClaim)))
//                        .build();
//                exchange = exchange.mutate().request(request).build();
//            }
//        }
//        return chain.filter(exchange);
//    }
//
//    private Claims validateToken(String token) {
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(secretKey)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
