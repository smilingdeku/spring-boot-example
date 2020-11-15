package org.example.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.common.domain.request.QueryRequest;
import org.example.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author linzhaoming
 * @since 2020/11/03
 **/
@SuppressWarnings("all")
public abstract class BaseController<S extends BaseService<M, E>, M extends BaseMapper<E>, E> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    protected S baseService;

    @Autowired
    public S getBaseService() {
        return baseService;
    }

    protected String getCurrentUsername() {
        String token = jwtTokenUtil.getToken(request);
        return jwtTokenUtil.getSubjectByToken(token);
    }

    protected QueryRequest mapToQuery(Map<String, Object> map)  {
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.putAll(map);
        return queryRequest;
    }
}
