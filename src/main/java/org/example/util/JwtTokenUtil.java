package org.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${token.header}")
    private String header;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.timeout}")
    private Long timeout;

    public String getToken(HttpServletRequest request) {
        return request.getHeader(header);
    }

    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(subject, claims);
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setSubject(subject)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + timeout * 1000))
                .compact();
    }

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + timeout * 1000))
                .compact();
    }

    public String refreshToken(String token) {
        Claims claims = getClaimsByToken(token);
        return generateToken(claims);
    }

    public boolean isTokenExpired(String token) {
        try {
            getExpirationByToken(token);
        } catch (ExpiredJwtException e) {
            return true;
        }
        return false;
    }

    public String getSubjectByToken(String token) {
        return getClaimByToken(token, Claims::getSubject);
    }

    public Date getExpirationByToken(String token) {
        return getClaimByToken(token, Claims::getExpiration);
    }

    private  <T> T getClaimByToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaimsByToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}