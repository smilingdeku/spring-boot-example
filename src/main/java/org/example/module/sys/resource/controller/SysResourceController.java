package org.example.module.sys.resource.controller;


import org.example.common.domain.ApiResult;
import org.example.common.domain.Router;
import org.example.module.sys.resource.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统资源 前端控制器
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceController {

    @Autowired
    private ISysResourceService resourceService;

    @GetMapping("/menu/{username}")
    public ResponseEntity<?> userMenuList(@PathVariable String username) {
        List<Router> routerList = resourceService.listRouterByUsername(username);
        return ResponseEntity.ok(ApiResult.success(routerList));
    }

}
