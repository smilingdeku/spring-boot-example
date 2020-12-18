package org.example.module.common.captcha.controller;

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
@RestController
@RequestMapping("/common/captcha")
public class CaptchaController {

    @Autowired
    private ICaptchaService captchaService;

    @GetMapping("/{type}")
    public Result captcha(@PathVariable Integer type) {
        String uuid = UUID.randomUUID().toString().replace("-", "");

        String image = captchaService.generateCaptcha(type, uuid);

        CaptchaResponse response = new CaptchaResponse();
        response.setKey(uuid);
        response.setImage(image);

        return Result.success(response);
    }

}
