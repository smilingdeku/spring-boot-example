package org.example.module.system.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.base.BaseController;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.Result;
import org.example.module.system.user.domain.entity.SysUser;
import org.example.module.system.user.domain.request.LoginRequest;
import org.example.module.system.user.domain.response.LoginResponse;
import org.example.module.system.user.domain.response.UserResponse;
import org.example.module.system.user.mapper.SysUserMapper;
import org.example.module.system.user.service.impl.SysUserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import java.util.Map;
import java.util.Objects;

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
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginRequest request) {
        String token = getBaseService().login(request.getUsername(), request.getPassword());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return Result.success(response);
    }

    @GetMapping
    public Result info() {
        UserResponse response = new UserResponse();
        SysUser user = getBaseService().getByUsername(getCurrentUsername());
        BeanUtils.copyProperties(user, response);
        response.setPermissions(getBaseService().listPermissionByUsername(getCurrentUsername()));
        return Result.success(response);
    }

    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = mapToQuery(requestParam);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.get("username"))) {
            queryWrapper.like(SysUser::getUsername, query.get("username"));
        }
        IPage<SysUser> page = getBaseService().page(new Page<>(query.getPageIndex(), query.getPageSize()),
                queryWrapper);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        SysUser sysUser = getBaseService().getById(id);
        return Result.success(sysUser);
    }

    @PreAuthorize("hasAuthority('system:user:add')")
    @PostMapping
    public Result save(@RequestBody SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        boolean success = getBaseService().save(sysUser);
        return success ? Result.success(sysUser) : Result.failure();
    }

    @PreAuthorize("hasAuthority('system:user:delete')")
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Long[] ids) {
//        return Result.success(getBaseService().removeByIds(Arrays.asList(ids)));
        return Result.success();
    }

    @PreAuthorize("hasAuthority('system:user:edit')")
    @PutMapping
    public Result update(@RequestBody SysUser sysUser) {
        SysUser old = getBaseService().getById(sysUser.getId());
        if (Objects.isNull(old)) {
            return Result.failure();
        }
        if (!old.getPassword().equals(sysUser.getPassword())) {
            String newPassword = passwordEncoder.encode(sysUser.getPassword());
            sysUser.setPassword(newPassword);
        }
        boolean success = getBaseService().updateById(sysUser);
        return success ? Result.success(sysUser) : Result.failure();
    }

}
