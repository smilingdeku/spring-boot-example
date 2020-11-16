package org.example.module.system.user.controller;


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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        IPage<SysUser> page = getBaseService().page(new Page<>(query.getPageIndex(), query.getPageSize()));
        return Result.page(page);
    }
}
