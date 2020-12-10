package org.example.common.util;

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

/**
 * Token 工具类
 */
@Component
public class TokenUtil implements Serializable {

    @Value("${token.header}")
    private String header;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.timeout}")
    private Long timeout;

    /**
     * 获取 Token
     *
     * @param request 请求
     * @return Token
     */
    public String getToken(HttpServletRequest request) {
        return request.getHeader(header);
    }

    /**
     * 生成 Token
     *
     * @param subject 主题
     * @return Token
     */
    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(subject, claims);
    }

    /**
     * 生成 Token
     *
     * @param subject 主题
     * @param claims  声明
     * @return Token
     */
    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setSubject(subject)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + timeout * 1000))
                .compact();
    }

    /**
     * 生成 Token
     *
     * @param claims 声明
     * @return Token
     */
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + timeout * 1000))
                .compact();
    }

    /**
     * 刷新 Token
     *
     * @param token 旧 Token
     * @return 新 Token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsByToken(token);
        return generateToken(claims);
    }

    /**
     * 检查 Token 是否过期
     *
     * @param token Token
     * @return boolean
     */
    public boolean isTokenExpired(String token) {
        try {
            getExpirationByToken(token);
        } catch (ExpiredJwtException e) {
            return true;
        }
        return false;
    }

    /**
     * 从 Token 中获取主题
     *
     * @param token Token
     * @return 主题
     */
    public String getSubjectByToken(String token) {
        return getClaimByToken(token, Claims::getSubject);
    }

    /**
     * 从 Token 中获取过期时间（若 Token 已过期，会抛异常）
     *
     * @param token Token
     * @return 过期时间
     */
    public Date getExpirationByToken(String token) {
        return getClaimByToken(token, Claims::getExpiration);
    }

    /**
     * 从 Token 中获取指定声明
     *
     * @param token          Token
     * @param claimsResolver 声明方法引用
     * @return 声明
     */
    private <T> T getClaimByToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaimsByToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从 Token 中获取所有声明
     *
     * @param token Token
     * @return 所有声明
     */
    private Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}