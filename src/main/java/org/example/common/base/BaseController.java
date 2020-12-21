package org.example.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.example.common.constant.CommonConstant;
import org.example.common.domain.request.QueryRequest;
import org.example.common.util.JsonUtil;
import org.example.common.util.TokenUtil;
import org.example.module.system.user.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

/**
 * @author linzhaoming
 * @since 2020/11/03
 **/
@SuppressWarnings("all")
public abstract class BaseController<S extends BaseService<M, E>, M extends BaseMapper<E>, E> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    protected S service;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public S getService() {
        return service;
    }

    /**
     * 获取用户名称
     *
     * @return String
     */
    protected String getCurrentUsername() {
        SysUser currentUser = getCurrentUser();
        return Objects.isNull(currentUser) ? null : currentUser.getUsername();
    }

    /**
     * 获取用户id
     *
     * @return Long
     */
    protected Long getCurrentUserId() {
        SysUser currentUser = getCurrentUser();
        return Objects.isNull(currentUser) ? null : currentUser.getId();
    }

    /**
     * 获取当前登录用户数据
     *
     * @return SysUser
     */
    protected SysUser getCurrentUser() {
        // fixme: 可以抽取出来成为方法 :"}
        String token = tokenUtil.getToken(request);
        String field = tokenUtil.getSubjectByToken(token);

        String sysUserInfoJson = (String) redisTemplate.opsForHash()
            .get(CommonConstant.REDIS_USER_INFO_HASH_KEY, field);

        if (StringUtils.isEmpty(sysUserInfoJson)) {
            return null;
        }

        return JsonUtil.parseObject(sysUserInfoJson, SysUser.class);
    }

    protected QueryRequest mapToQuery(Map<String, Object> map) {
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.putAll(map);
        return queryRequest;
    }
}
