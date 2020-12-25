package org.example.module.common.captcha.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.example.common.domain.response.Result;
import org.example.module.common.captcha.domain.response.CaptchaResponse;
import org.example.module.common.captcha.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author linzhaoming
 * @since 2020-12-16
 **/
@Api(tags = {"验证码接口"})
@RestController
@RequestMapping("/common/captcha")
public class CaptchaController {

    @Autowired
    private ICaptchaService captchaService;

    @ApiOperation("获取验证码")
    @ApiImplicitParam(paramType="path", name = "type", value = "验证码类型 [1-后台登录]", required = true, dataTypeClass = Integer.class)
    @GetMapping("/{type}")
    public Result<CaptchaResponse> captcha(@PathVariable Integer type) {
        String uuid = UUID.randomUUID().toString().replace("-", "");

        String image = captchaService.generateCaptcha(type, uuid);

        CaptchaResponse response = new CaptchaResponse();
        response.setKey(uuid);
        response.setImage(image);

        return Result.success(response);
    }

}
