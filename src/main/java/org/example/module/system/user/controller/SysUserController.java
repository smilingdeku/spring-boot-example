package org.example.module.system.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.base.BaseController;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.Result;
import org.example.common.util.BeanCopyUtil;
import org.example.common.util.ListUtil;
import org.example.module.system.user.domain.entity.SysUser;
import org.example.module.system.user.domain.request.LoginRequest;
import org.example.module.system.user.domain.request.SysUserRequest;
import org.example.module.system.user.domain.response.LoginResponse;
import org.example.module.system.user.domain.response.UserResponse;
import org.example.module.system.user.mapper.SysUserMapper;
import org.example.module.system.user.service.impl.SysUserServiceImpl;
import org.example.module.system.userrole.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author linzhaoming
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUserServiceImpl, SysUserMapper, SysUser> {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginRequest request) {
        String token = getBaseService().login(request.getUsername(), request.getPassword());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return Result.success(response);
    }

    @GetMapping
    public Result info() {
        SysUser user = getBaseService().getByUsername(getCurrentUsername());
        UserResponse response = BeanCopyUtil.map(user, UserResponse.class);
        response.setPermissions(getBaseService().listPermissionByUsername(getCurrentUsername()));
        return Result.success(response);
    }

    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = mapToQuery(requestParam);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            queryWrapper.lambda().like(SysUser::getUsername, query.getKeyword());
        }
        if (!StringUtils.isEmpty(query.getLineOrderField())) {
            queryWrapper.orderBy(true, query.getIsAsc(), query.getLineOrderField());
        }
        IPage<SysUser> page = getBaseService()
            .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        return Result.success(page);
    }

    @PreAuthorize("hasAuthority('system:user:edit')")
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        SysUser sysUser = getBaseService().getById(id);
        return Result.success(sysUser);
    }

    @PreAuthorize("hasAuthority('system:user:add')")
    @PostMapping
    public Result save(@RequestBody SysUserRequest request) {
        SysUser sysUser = getBaseService().saveUserAndRoles(request);
        return Result.success(sysUser);
    }

    @PreAuthorize("hasAuthority('system:user:delete')")
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Long[] ids) {
        // 去除admin
        List<Long> idList = ListUtil.arr2List(ids);
        ListUtil.removeIf(idList, (e) -> e.equals(1L));

        if (ListUtil.isNotEmpty(idList)) {
            idList.forEach(id -> {
                getBaseService().removeById(id);
                sysUserRoleService.deleteByUserId(id);
            });
        }
        return Result.success();
    }

    @PreAuthorize("hasAuthority('system:user:edit')")
    @PutMapping
    public Result update(@RequestBody SysUserRequest request) {
        SysUser sysUser = getBaseService().updateUserAndRoles(request);
        return Result.success(sysUser);
    }

    @PreAuthorize("hasAnyAuthority('system:user:add', 'system:user:edit')")
    @GetMapping("/{id}/roles")
    public Result userRoles(@PathVariable Long id) {
        List<Long> roleIds = sysUserRoleService.listRoleIdByUserId(id);
        return Result.success(roleIds);
    }

}
