package org.example.module.system.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.annotation.Log;
import org.example.common.base.BaseController;
import org.example.common.constant.CommonConstant;
import org.example.common.domain.entity.Router;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.PageResult;
import org.example.common.domain.response.Result;
import org.example.common.util.MapperUtil;
import org.example.common.util.TreeUtil;
import org.example.module.system.resource.domain.dto.SysResourceDTO;
import org.example.module.system.resource.domain.entity.SysResource;
import org.example.module.system.resource.domain.request.SysResourceRequest;
import org.example.module.system.resource.domain.response.ResourceTreeNodeResponse;
import org.example.module.system.resource.domain.response.RouterResponse;
import org.example.module.system.resource.domain.response.SysResourceResponse;
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
@Api(tags = {"系统资源接口"})
@RestController
@RequestMapping("/sys/resource")
public class SysResourceController extends BaseController<SysResourceServiceImpl, SysResourceMapper, SysResource> {

    @ApiOperation(value = "获取路由列表")
    @GetMapping("/routers")
    public Result<List<RouterResponse>> getRouters() {
        List<Router> list = getService().listRouterByUsername(getCurrentUsername());
        List<RouterResponse> responseList = MapperUtil.mapList(list, Router.class, RouterResponse.class);
        return Result.success(TreeUtil.build(responseList, CommonConstant.TOP_RESOURCE_PARENT_ID));
    }

    @ApiOperation(value = "获取资源信息")
    @PreAuthorize("hasAuthority('system:resource:edit')")
    @GetMapping("/{id}")
    public Result<SysResourceResponse> get(@PathVariable Long id) {
        SysResourceDTO dto = getService().getById(id);
        SysResourceResponse response = MapperUtil.map(dto, SysResourceResponse.class);
        return Result.success(response);
    }

    @ApiOperation(value = "获取资源分页数据")
    @GetMapping("/page")
    public PageResult<SysResourceResponse> page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = QueryRequest.from(requestParam);
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            queryWrapper.like(SysResource::getName, query.getKeyword());
        }
        IPage<SysResource> page = getService()
                .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        List<SysResourceResponse> responseList = MapperUtil.mapList(page.getRecords(), SysResource.class, SysResourceResponse.class);
        return PageResult.build(responseList, page.getTotal());
    }

    @ApiOperation(value = "获取资源列表")
    @PreAuthorize("hasAuthority('system:resource')")
    @GetMapping("/list")
    public Result<List<ResourceTreeNodeResponse>> list() {
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(SysResource::getSortNumber);
        List<SysResource> resourceList = getService().list(queryWrapper);
        List<ResourceTreeNodeResponse> responseList = MapperUtil.mapList(resourceList, SysResource.class, ResourceTreeNodeResponse.class);
        return Result.success( TreeUtil.build(responseList, CommonConstant.TOP_RESOURCE_PARENT_ID));
    }

    @ApiOperation(value = "添加资源")
    @Log
    @PreAuthorize("hasAuthority('system:resource:add')")
    @PostMapping
    public Result<SysResourceResponse> save(@RequestBody SysResourceRequest request) {
        SysResource sysResource = MapperUtil.map(request, SysResource.class);
        boolean success = getService().save(sysResource);
        return success ? Result.success(MapperUtil.map(sysResource, SysResourceResponse.class)) : Result.failure();
    }

    @ApiOperation(value = "删除资源")
    @Log
    @PreAuthorize("hasAuthority('system:resource:delete')")
    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        idList.forEach(getService()::deleteResource);
        return Result.success();
    }

    @ApiOperation(value = "更新资源")
    @Log
    @PreAuthorize("hasAuthority('system:resource:edit')")
    @PutMapping
    public Result<SysResourceResponse> update(@RequestBody SysResourceRequest request) {
        SysResource sysResource = MapperUtil.map(request, SysResource.class);
        boolean success = getService().updateById(sysResource);
        return success ? Result.success(MapperUtil.map(sysResource, SysResourceResponse.class)) : Result.failure();
    }

}
