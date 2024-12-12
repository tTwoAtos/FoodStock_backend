package org.aelion.authentication.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {
    private final String jwtSecret = "h3j52B75jBV5Jhvk5b5K7HBk4hB12H5K8bBK8b8hjB2H4JbBK1J9b3KBH4JV5HV2hjv8hgcf7d8xwf1gkl5ioLK";
    private final long tokenExpiration = 3600L;

    /**
     * Génère un token JWT avec les informations de l'utilisateur
     *
     * @param userId identifiant de l'utilisateur
     * @param role   Rôles de l'utilisateur
     * @return token JWT signé
     */
    public String generateToken(Long userId, String role) {
        Instant now = Instant.now();
        Instant expiration = now.plus(tokenExpiration, ChronoUnit.SECONDS);

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    /**
     * Vérifie si un token est valide
     *
     * @param token token JWT à vérifier
     * @return true si le token est valide, false sinon
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrait l'identifiant utilisateur du token
     *
     * @param token token JWT
     * @return identifiant de l'utilisateur
     */
    public String extractUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Extrait les rôles de l'utilisateur à partir du token
     *
     * @param token token JWT
     * @return Rôle de l'utilisateur
     */
    public String extractRoles(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}