package org.example.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.common.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

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

    public S getService() {
        return service;
    }

    /**
     * 获取用户名称
     *
     * @return String
     */
    protected String getCurrentUsername() {
        String token = tokenUtil.getToken(request);
        return tokenUtil.getSubjectByToken(token);
    }

}
