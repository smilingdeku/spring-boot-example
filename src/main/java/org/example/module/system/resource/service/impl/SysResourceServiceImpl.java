package org.example.module.system.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.example.common.base.BaseService;
import org.example.common.domain.entity.Router;
import org.example.common.domain.entity.RouterMeta;
import org.example.common.util.TreeUtil;
import org.example.module.system.resource.domain.dto.ResourceTreeNode;
import org.example.module.system.resource.domain.dto.SysResourceDTO;
import org.example.module.system.resource.domain.entity.SysResource;
import org.example.module.system.resource.mapper.SysResourceMapper;
import org.example.module.system.resource.service.ISysResourceService;
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
            Router router = new Router();
            router.setId(Long.toString(resource.getId()));
            router.setParentId(null == resource.getParentId() ? null : Long.toString(resource.getParentId()));
            router.setAlwaysShow(null == resource.getParentId());
            router.setPath(resource.getPath());
            router.setComponent(resource.getComponent());
            router.setName(resource.getName());
            router.setMeta(new RouterMeta(resource.getName(), resource.getIcon(), null != resource.getParentId()));
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
