package org.example.module.system.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.annotation.Log;
import org.example.common.base.BaseController;
import org.example.common.domain.entity.Router;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.Result;
import org.example.module.system.resource.domain.entity.SysResource;
import org.example.module.system.resource.mapper.SysResourceMapper;
import org.example.module.system.resource.service.impl.SysResourceServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
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

import java.util.Arrays;
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
        List<Router> routerList = getService().listRouterByUsername(getCurrentUsername());
        return Result.success(routerList);
    }

    @PreAuthorize("hasAuthority('system:resource:edit')")
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        return Result.success(getService().getById(id));
    }

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = QueryRequest.from(requestParam);
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            queryWrapper.like(SysResource::getName, query.getKeyword());
        }
        IPage<SysResource> page = getService()
                .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        return Result.success(page);
    }

    @PreAuthorize("hasAuthority('system:resource')")
    @GetMapping("/list")
    public Result list() {
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(SysResource::getSortNumber);
        return Result.success(getService().listResourceTreeNode(queryWrapper));
    }

    @Log
    @PreAuthorize("hasAuthority('system:resource:add')")
    @PostMapping
    public Result save(@RequestBody SysResource sysResource) {
        boolean success = getService().save(sysResource);
        return success ? Result.success(sysResource) : Result.failure();
    }

    @Log
    @PreAuthorize("hasAuthority('system:resource:delete')")
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        idList.forEach(getService()::deleteResource);
        return Result.success();
    }

    @Log
    @PreAuthorize("hasAuthority('system:resource:edit')")
    @PutMapping
    public Result update(@RequestBody SysResource sysResource) {
        boolean success = getService().updateById(sysResource);
        return success ? Result.success(sysResource) : Result.failure();
    }

}
