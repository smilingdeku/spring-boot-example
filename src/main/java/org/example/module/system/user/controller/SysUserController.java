package org.example.module.system.user.controller;


import org.example.common.base.BaseController;
import org.example.common.domain.Result;
import org.example.module.system.user.domain.entity.SysUser;
import org.example.module.system.user.domain.request.LoginRequest;
import org.example.module.system.user.domain.response.LoginResponse;
import org.example.module.system.user.domain.response.UserResponse;
import org.example.module.system.user.mapper.SysUserMapper;
import org.example.module.system.user.service.ISysUserService;
import org.example.module.system.user.service.impl.SysUserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ISysUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginRequest request) {
        String token = userService.login(request.getUsername(), request.getPassword());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return Result.success(response);
    }

    @GetMapping
    public Result info() {
        UserResponse response = new UserResponse();
        SysUser user = userService.getByUsername(getCurrentUsername());
        BeanUtils.copyProperties(user, response);
        response.setPermissions(userService.listPermissionByUsername(getCurrentUsername()));
        return Result.success(response);
    }
}
