package com.alshadowstechnologies.productmanager.config.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {

    private static final Long EXPIRATION_AFTER = 86_400_000L; // 1 day

    @Value("${jwt.token.secret}")
    private String secret;

    private static final String TOKEN_PREFIX = "Bearer ";

    private static final String HEADER = "Authorization";

    private SecretKey getKey() {
        final var keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(final String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_AFTER))
                .signWith(getKey())
                .compact();
    }

    public String getAuthUser(final HttpServletRequest request) {
        final var token = request.getHeader(HEADER);
        if (token != null) {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token.replace(TOKEN_PREFIX, ""))
                    .getPayload()
                    .getSubject();
        }
        return null;
    }

}
