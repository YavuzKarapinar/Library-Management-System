package me.jazzy.librarymanagementsystem.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import me.jazzy.librarymanagementsystem.exception.notfound.JwtNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtGenerator {

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(secretKey())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private SecretKey secretKey() {
        String secret = "19283109asdklasd;adasd;1'!+%1a32";
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public boolean validateToken(String token) {
        try {
            if (!isTokenExpired(token)) {
                Jwts.parser()
                        .verifyWith(secretKey())
                        .build()
                        .parseSignedClaims(token);
                return true;
            }
        } catch (JwtException exception) {
            throw new JwtNotFoundException("Jwt expired or incorrect");
        }
        return false;
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
