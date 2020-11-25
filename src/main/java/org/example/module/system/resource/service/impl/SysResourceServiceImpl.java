package org.example.module.system.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import ma.glasnost.orika.metadata.Type;
import org.example.common.base.BaseService;
import org.example.common.domain.entity.Router;
import org.example.common.domain.entity.RouterMeta;
import org.example.common.util.BeanCopyUtil;
import org.example.common.util.TreeUtil;
import org.example.module.system.resource.domain.dto.ResourceTreeNode;
import org.example.module.system.resource.domain.dto.SysResourceDTO;
import org.example.module.system.resource.domain.entity.SysResource;
import org.example.module.system.resource.mapper.SysResourceMapper;
import org.example.module.system.resource.service.ISysResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统资源 服务实现类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
@Service
public class SysResourceServiceImpl extends BaseService<SysResourceMapper, SysResource> implements ISysResourceService {

    @Override
    public List<SysResource> listByUsernameAndType(String username, int type) {
        return this.getBaseMapper().listByUsernameAndType(username, type);
    }

    @Override
    public List<Router> listRouterByUsername(String username) {
        List<SysResource> resourceList = this.listByUsernameAndType(username, SysResource.TYPE_MENU);
        List<Router> routerList = new ArrayList<>();
        for (SysResource resource : resourceList) {
            boolean isTopResource = Objects.isNull(resource.getParentId());

            Router router = BeanCopyUtil.map(resource, Router.class);
            router.setAlwaysShow(isTopResource);
            router.setMeta(new RouterMeta(resource.getName(), resource.getIcon(), !isTopResource));

            routerList.add(router);
        }

        return TreeUtil.build(routerList, "0");
    }

    @Override
    public List<ResourceTreeNode> listResourceTreeNode(Long roleId) {
        List<ResourceTreeNode> nodeList = this.getBaseMapper().listResourceTreeNode(roleId);
        return TreeUtil.build(nodeList, "0");
    }

    @Override
    public List<ResourceTreeNode> listResourceTreeNode(Wrapper<SysResource> queryWrapper) {
        List<SysResource> resourceList = this.list(queryWrapper);
        Type<SysResource> resourceType = BeanCopyUtil.getType(SysResource.class);
        Type<ResourceTreeNode> treeNodeType = BeanCopyUtil.getType(ResourceTreeNode.class);
        List<ResourceTreeNode> treeNodeList = BeanCopyUtil.mapList(resourceList, resourceType, treeNodeType);
        return TreeUtil.build(treeNodeList, "0");
    }

    @Override
    public SysResourceDTO getById(Long id) {
        return getBaseMapper().getById(id);
    }

}
