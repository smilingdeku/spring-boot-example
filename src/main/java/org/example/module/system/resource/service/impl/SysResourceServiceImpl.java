package org.example.module.system.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import ma.glasnost.orika.metadata.Type;
import org.example.common.base.BaseService;
import org.example.common.constant.CommonConstant;
import org.example.common.domain.entity.Router;
import org.example.common.domain.entity.RouterMeta;
import org.example.common.util.MapperUtil;
import org.example.common.util.TreeUtil;
import org.example.module.system.resource.domain.dto.ResourceTreeNode;
import org.example.module.system.resource.domain.dto.SysResourceDTO;
import org.example.module.system.resource.domain.entity.SysResource;
import org.example.module.system.resource.mapper.SysResourceMapper;
import org.example.module.system.resource.service.ISysResourceService;
import org.example.module.system.roleresource.service.ISysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ISysRoleResourceService sysRoleResourceService;

    @Override
    public List<SysResource> listByUsernameAndType(String username, int type) {
        return this.getBaseMapper().listByUsernameAndType(username, type);
    }

    @Override
    public List<Router> listRouterByUsername(String username) {
        List<SysResource> resourceList = this.listByUsernameAndType(username, SysResource.TYPE_MENU);
        List<Router> routerList = new ArrayList<>();
        for (SysResource resource : resourceList) {
            boolean isTopResource = CommonConstant.TOP_RESOURCE_PARENT_ID
                    .equals(Long.toString(resource.getParentId()));

            Router router = MapperUtil.map(resource, Router.class);
            router.setAlwaysShow(isTopResource);
            router.setMeta(new RouterMeta(resource.getName(), resource.getIcon(), !isTopResource));

            routerList.add(router);
        }
        return routerList;
    }

    @Override
    public List<ResourceTreeNode> listResourceTreeNode(Long roleId) {
        return this.getBaseMapper().listResourceTreeNode(roleId);
    }

    @Override
    public List<ResourceTreeNode> listResourceTreeNode(Wrapper<SysResource> queryWrapper) {
        List<SysResource> resourceList = this.list(queryWrapper);
        Type<SysResource> resourceType = MapperUtil.getType(SysResource.class);
        Type<ResourceTreeNode> treeNodeType = MapperUtil.getType(ResourceTreeNode.class);
        List<ResourceTreeNode> treeNodeList = MapperUtil.mapList(resourceList, resourceType, treeNodeType);
        return TreeUtil.build(treeNodeList, CommonConstant.TOP_RESOURCE_PARENT_ID);
    }

    @Override
    public SysResourceDTO getById(Long id) {
        return getBaseMapper().getById(id);
    }

    @Transactional
    @Override
    public void deleteResource(Long id) {
        this.removeById(id);
        sysRoleResourceService.deleteByResourceId(id);
    }

}
