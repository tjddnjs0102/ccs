package org.ccs.app.core.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.access.expiration-ms}")
    private int jwtExpirationInMs;

    @Value("${jwt.token.refresh.expiration-ms}")
    private int refreshExpirationInMs;


    private String generateTokenBase(Long userId, int expirationInMs) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateToken(Long userId) {
        return generateTokenBase(userId, jwtExpirationInMs);
    }

    public String generateRefreshToken(Long userId) {
        return generateTokenBase(userId, refreshExpirationInMs);
    }
}
