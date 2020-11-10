package org.example.module.sys.resource.controller;


import org.example.common.base.BaseController;
import org.example.common.domain.Result;
import org.example.common.domain.Router;
import org.example.module.sys.resource.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class SysResourceController extends BaseController {

    @Autowired
    private ISysResourceService resourceService;

    @GetMapping("/routers")
    public Result<List<Router>> getRouters() {
        List<Router> routerList = resourceService.listRouterByUsername(getCurrentUsername());
        return Result.success(routerList);
    }

}
