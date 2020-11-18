package org.example.module.system.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.base.BaseController;
import org.example.common.domain.entity.Router;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.Result;
import org.example.module.system.resource.domain.entity.SysResource;
import org.example.module.system.resource.mapper.SysResourceMapper;
import org.example.module.system.resource.service.impl.SysResourceServiceImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统资源 前端控制器
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceController extends BaseController<SysResourceServiceImpl, SysResourceMapper, SysResource> {

    @GetMapping("/routers")
    public Result getRouters() {
        List<Router> routerList = getBaseService().listRouterByUsername(getCurrentUsername());
        return Result.success(routerList);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Serializable id) {
        return Result.success(getBaseService().getById(id));
    }

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = mapToQuery(requestParam);
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.get("name"))) {
            queryWrapper.like(SysResource::getName, query.get("name"));
        }
        IPage<SysResource> page = getBaseService().page(new Page<>(query.getPageIndex(), query.getPageSize()),
                queryWrapper);
        return Result.success(page);
    }

    @PostMapping
    public Result save(@RequestBody SysResource sysResource) {
        return Result.success(sysResource);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Long[] ids) {
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody SysResource sysResource) {
        return Result.success(sysResource);
    }

}
