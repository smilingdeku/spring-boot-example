package org.example.module.sys.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.domain.Router;
import org.example.common.domain.RouterMeta;
import org.example.common.util.TreeUtil;
import org.example.module.sys.resource.domain.entity.SysResource;
import org.example.module.sys.resource.mapper.SysResourceMapper;
import org.example.module.sys.resource.service.ISysResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统资源 服务实现类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Override
    public List<SysResource> listByUsernameAndType(String username, int type) {
        return this.getBaseMapper().listByUsernameAndType(username, type);
    }

    @Override
    public List<Router> listRouterByUsername(String username) {
        List<SysResource> resourceList = this.listByUsernameAndType(username, SysResource.TYPE_MENU);
        List<Router> routerList = new ArrayList<>();
        for (SysResource resource : resourceList) {
            Router router = new Router();
            router.setId(Long.toString(resource.getId()));
            router.setParentId(null == resource.getParentId() ? null : Long.toString(resource.getParentId()));
            router.setPath(resource.getPath());
            router.setComponent(resource.getComponent());
            router.setName(resource.getName());
            router.setMeta(new RouterMeta(resource.getName(), resource.getIcon()));
            routerList.add(router);
        }
        return TreeUtil.build(routerList, null);
    }
}
