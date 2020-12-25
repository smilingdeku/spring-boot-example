package org.example.module.system.role.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.annotation.Log;
import org.example.common.base.BaseController;
import org.example.common.constant.CommonConstant;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.PageResult;
import org.example.common.domain.response.Result;
import org.example.common.util.MapperUtil;
import org.example.common.util.TreeUtil;
import org.example.module.system.resource.domain.dto.ResourceTreeNode;
import org.example.module.system.resource.domain.response.ResourceTreeNodeResponse;
import org.example.module.system.resource.service.ISysResourceService;
import org.example.module.system.role.domain.entity.SysRole;
import org.example.module.system.role.domain.request.SysRoleRequest;
import org.example.module.system.role.domain.response.SysRoleResponse;
import org.example.module.system.role.mapper.SysRoleMapper;
import org.example.module.system.role.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 系统角色 前端控制器
 * </p>
 *
 * @author linzhaoming
 * @since 2020-10-26
 */
@Api(tags = {"系统角色接口"})
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController<SysRoleServiceImpl, SysRoleMapper, SysRole> {

    @Autowired
    private ISysResourceService sysResourceService;

    @ApiOperation(value = "获取角色分页数据")
    @PreAuthorize("hasAuthority('system:role')")
    @GetMapping("/page")
    public PageResult<SysRoleResponse> page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = QueryRequest.from(requestParam);
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            queryWrapper.lambda().like(SysRole::getName, query.getKeyword());
        }
        if (!StringUtils.isEmpty(query.getOrderField())) {
            queryWrapper.orderBy(true, query.getIsAsc(), query.getLineOrderField());
        }
        IPage<SysRole> page = getService()
                .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        List<SysRoleResponse> responseList = MapperUtil.mapList(page.getRecords(), SysRole.class, SysRoleResponse.class);
        return PageResult.build(responseList, page.getTotal());
    }

    @ApiOperation(value = "获取角色列表")
    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/list")
    public Result<List<SysRoleResponse>> list() {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        List<SysRole> list = getService().list(queryWrapper);
        List<SysRoleResponse> responseList = MapperUtil.mapList(list, SysRole.class, SysRoleResponse.class);
        return Result.success(responseList);
    }

    @ApiOperation(value = "获取角色信息")
    @PreAuthorize("hasAuthority('system:role:edit')")
    @GetMapping("/{id}")
    public Result<SysRoleResponse> get(@PathVariable Long id) {
        SysRole sysRole = getService().getById(id);
        SysRoleResponse response = MapperUtil.map(sysRole, SysRoleResponse.class);
        return Result.success(response);
    }

    @ApiOperation(value = "保存角色")
    @Log
    @PreAuthorize("hasAuthority('system:role:add')")
    @PostMapping
    public Result<SysRoleResponse> save(@RequestBody SysRoleRequest request) {
        SysRole sysRole = getService().saveRoleAndResources(request);
        SysRoleResponse response = MapperUtil.map(sysRole, SysRoleResponse.class);
        return Result.success(response);
    }

    @ApiOperation(value = "删除角色")
    @Log
    @PreAuthorize("hasAuthority('system:role:delete')")
    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        idList.forEach(getService()::deleteRoleAndResources);
        return Result.success();
    }

    @ApiOperation(value = "更新角色")
    @Log
    @PreAuthorize("hasAuthority('system:role:edit')")
    @PutMapping
    public Result<SysRole> update(@RequestBody SysRoleRequest request) {
        SysRole sysRole = getService().updateRoleAndResources(request);
        return Result.success(sysRole);
    }

    @ApiOperation(value = "获取角色资源列表")
    @PreAuthorize("hasAnyAuthority('system:role:add', 'system:role:edit')")
    @GetMapping("/{id}/resources")
    public Result<List<ResourceTreeNodeResponse>> roleResources(@PathVariable Long id) {
        List<ResourceTreeNode> treeNodeList = sysResourceService.listResourceTreeNode(id);
        List<ResourceTreeNodeResponse> responseList = MapperUtil.mapList(treeNodeList, ResourceTreeNode.class, ResourceTreeNodeResponse.class);
        return Result.success(TreeUtil.build(responseList, CommonConstant.TOP_RESOURCE_PARENT_ID));
    }

}
