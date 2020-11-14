package org.example.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.domain.Result;
import org.example.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

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

    @GetMapping("/{id}")
    public Result<E> get(@PathVariable Serializable id) {
        E entity = getBaseService().getById(id);
        return Result.success(entity);
    }

    @GetMapping("/page")
    public Result<IPage<E>> page() {
        IPage<E> page = getBaseService().page(new Page<E>());
        return Result.success(page);
    }

}
