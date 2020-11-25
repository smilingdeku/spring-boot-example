package org.example.module.system.role.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.base.BaseController;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.Result;
import org.example.module.system.resource.service.ISysResourceService;
import org.example.module.system.role.domain.entity.SysRole;
import org.example.module.system.role.domain.request.SysRoleRequest;
import org.example.module.system.role.mapper.SysRoleMapper;
import org.example.module.system.role.service.impl.SysRoleServiceImpl;
import org.example.module.system.roleresource.service.ISysRoleResourceService;
import org.example.module.system.userrole.service.ISysUserRoleService;
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
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController<SysRoleServiceImpl, SysRoleMapper, SysRole> {

    @Autowired
    private ISysResourceService sysResourceService;
    @Autowired
    private ISysRoleResourceService sysRoleResourceService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @PreAuthorize("hasAuthority('system:role')")
    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = mapToQuery(requestParam);
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            queryWrapper.like(SysRole::getName, query.getKeyword());
        }
        IPage<SysRole> page = getBaseService()
                .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        return Result.success(page);
    }

    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/list")
    public Result list() {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        List<SysRole> list = getBaseService().list(queryWrapper);
        return Result.success(list);
    }

    @PreAuthorize("hasAuthority('system:role:edit')")
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        return Result.success(getBaseService().getById(id));
    }

    @PreAuthorize("hasAuthority('system:role:add')")
    @PostMapping
    public Result save(@RequestBody SysRoleRequest request) {
        SysRole sysRole = getBaseService().saveRoleAndResources(request);
        return Result.success(sysRole);
    }

    @PreAuthorize("hasAuthority('system:role:delete')")
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        idList.forEach(id -> {
            getBaseService().removeById(id);
            sysUserRoleService.deleteByRoleId(id);
            sysRoleResourceService.deleteByRoleId(id);
        });
        return Result.success();
    }

    @PreAuthorize("hasAuthority('system:role:edit')")
    @PutMapping
    public Result update(@RequestBody SysRoleRequest request) {
        SysRole sysRole = getBaseService().updateRoleAndResources(request);
        return Result.success(sysRole);
    }

    @PreAuthorize("hasAnyAuthority('system:role:add', 'system:role:edit')")
    @GetMapping("/{id}/resources")
    public Result roleResources(@PathVariable Long id) {
        return Result.success(sysResourceService.listResourceTreeNode(id));
    }

}
