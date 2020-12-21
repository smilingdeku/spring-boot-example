package org.example.config.security.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.example.common.constant.CommonConstant;
import org.example.common.util.JsonUtil;
import org.example.common.util.TokenUtil;
import org.example.module.system.user.domain.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain) throws IOException, ServletException {
        final String token = tokenUtil.getToken(request);

        if (!StringUtils.isEmpty(token)) {
            try {
                SysUser sysUser = getUserInfo(token);
                if (Objects.nonNull(sysUser)) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(sysUser.getUsername());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        sysUser.getUsername(), null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT: {} failed: {}", token, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT: {} failed: {}", token, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT: {} failed: {}", token, exception.getMessage());
            } catch (SignatureException exception) {
                log.warn("Request to parse JWT with invalid signature: {} failed: {}", token, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT: {} failed: {}", token, exception.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 根据token获取用户数据
     *
     * @param token token
     * @return SysUser
     */
    private SysUser getUserInfo(String token) {
        // subject为userName的md5字符串
        String redisField = tokenUtil.getSubjectByToken(token);
        String sysUserInfoJson = (String) redisTemplate.opsForHash()
            .get(CommonConstant.REDIS_USER_INFO_HASH_KEY, redisField);

        if (StringUtils.isEmpty(sysUserInfoJson)) {
            return null;
        }
        return JsonUtil.parseObject(sysUserInfoJson, SysUser.class);
    }

}
