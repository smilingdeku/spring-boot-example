package org.example.module.sys.user.controller;


import org.example.common.base.BaseController;
import org.example.common.domain.ApiResult;
import org.example.module.sys.user.domain.entity.SysUser;
import org.example.module.sys.user.domain.request.LoginRequest;
import org.example.module.sys.user.domain.response.LoginResponse;
import org.example.module.sys.user.domain.response.UserResponse;
import org.example.module.sys.user.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequest request) {
        String token = userService.login(request.getUsername(), request.getPassword());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return ResponseEntity.ok(ApiResult.success(response));
    }

    @GetMapping
    public ResponseEntity<?> info() {
        UserResponse response = new UserResponse();
        SysUser user = userService.getByUsername(getCurrentUsername());
        BeanUtils.copyProperties(user, response);
        response.setPermissions(userService.listPermissionByUsername(getCurrentUsername()));
        return ResponseEntity.ok(ApiResult.success(response));
    }
}
