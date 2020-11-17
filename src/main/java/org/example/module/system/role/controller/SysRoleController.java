package org.example.module.system.role.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.base.BaseController;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.Result;
import org.example.module.system.role.domain.entity.SysRole;
import org.example.module.system.role.mapper.SysRoleMapper;
import org.example.module.system.role.service.impl.SysRoleServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Arrays;
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

    @PreAuthorize("hasAuthority('system:role')")
    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = mapToQuery(requestParam);
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.get("name"))) {
            queryWrapper.like(SysRole::getName, query.get("name"));
        }
        IPage<SysRole> page = getBaseService().page(new Page<>(query.getPageIndex(), query.getPageSize()),
                queryWrapper);
        return Result.success(page);
    }

    @PreAuthorize("hasAuthority('system:role')")
    @GetMapping("/{ids}")
    public Result delete(@PathVariable Serializable[] ids) {
        return Result.success(getBaseService().removeByIds(Arrays.asList(ids)));
    }

    @PostMapping
    public Result save(@RequestBody SysRole sysRole) {
        return Result.success(sysRole);
    }

}
