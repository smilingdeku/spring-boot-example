package org.example.module.sys.user.controller;


import org.example.common.domain.ResultData;
import org.example.common.enums.Code;
import org.example.module.sys.user.domain.request.LoginRequest;
import org.example.module.sys.user.domain.response.TokenResponse;
import org.example.module.sys.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequest request) {
        String token = sysUserService.login(request.getUsername(), request.getPassword());
        TokenResponse response = new TokenResponse();
        response.setToken(token);
        return ResponseEntity.ok(new ResultData<>(Code.SUCCESS, response));
    }
}
