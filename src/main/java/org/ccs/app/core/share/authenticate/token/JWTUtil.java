package org.ccs.app.core.share.authenticate.token;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
public class JWTUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generate(JWTType type, Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600);

        return Jwts.builder()
                .setSubject(Long.toString(userId)) // 토큰 subject를 사용자 ID로 설정
                .setIssuedAt(now)
                .setExpiration(expiryDate) // 만료시간 1시간
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // 토큰 서명
                .compact();
    }

    public boolean verify(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Object decode(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
