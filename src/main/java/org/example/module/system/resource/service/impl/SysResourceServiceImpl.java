package org.example.module.system.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        return TreeUtil.build(routerList, null);
    }

    @Override
    public List<ResourceTreeNode> listResourceTreeNode(Long roleId) {
        List<ResourceTreeNode> nodeList = this.getBaseMapper().listResourceTreeNode(roleId);
        return TreeUtil.build(nodeList, null);
    }

    @Override
    public List<ResourceTreeNode> listResourceTreeNode(Wrapper<SysResource> queryWrapper) {
        List<SysResource> resourceList = this.list(queryWrapper);
        List<ResourceTreeNode> treeNodeList = new ArrayList<>();
        for (SysResource resource : resourceList) {
            ResourceTreeNode treeNode = new ResourceTreeNode();
            treeNode.setId(Long.toString(resource.getId()));
            treeNode.setParentId(null == resource.getParentId() ? null : Long.toString(resource.getParentId()));
            treeNode.setType(resource.getType());
            treeNode.setName(resource.getName());
            treeNode.setIcon(resource.getIcon());
            treeNode.setPath(resource.getPath());
            treeNode.setComponent(resource.getComponent());
            treeNode.setPermission(resource.getPermission());
            treeNode.setSortNumber(resource.getSortNumber());
            treeNodeList.add(treeNode);
        }
        return TreeUtil.build(treeNodeList, null);
    }

    @Override
    public SysResourceDTO getById(Long id) {
        return getBaseMapper().getById(id);
    }

}
