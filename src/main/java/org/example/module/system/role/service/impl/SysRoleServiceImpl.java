package org.example.module.system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.base.BaseService;
import org.example.common.util.MapperUtil;
import org.example.module.system.role.domain.entity.SysRole;
import org.example.module.system.role.domain.request.SysRoleRequest;
import org.example.module.system.role.mapper.SysRoleMapper;
import org.example.module.system.role.service.ISysRoleService;
import org.example.module.system.roleresource.domain.entity.SysRoleResource;
import org.example.module.system.roleresource.service.ISysRoleResourceService;
import org.example.module.system.userrole.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-10-26
 */
@Service
public class SysRoleServiceImpl extends BaseService<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysRoleResourceService sysRoleResourceService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Transactional
    @Override
    public SysRole saveRoleAndResources(SysRoleRequest request) {
        SysRole sysRole = MapperUtil.map(request, SysRole.class);
        this.save(sysRole);
        if (!CollectionUtils.isEmpty(request.getResourceIds())) {
            request.getResourceIds().forEach(resourceId -> {
                SysRoleResource sysRoleResource = new SysRoleResource();
                sysRoleResource.setRoleId(sysRole.getId());
                sysRoleResource.setResourceId(resourceId);
                sysRoleResourceService.save(sysRoleResource);
            });
        }
        return sysRole;
    }

    @Transactional
    @Override
    public SysRole updateRoleAndResources(SysRoleRequest request) {
        SysRole sysRole = MapperUtil.map(request, SysRole.class);
        this.updateById(sysRole);
        List<SysRoleResource> roleResourceList = sysRoleResourceService.list(new LambdaQueryWrapper<SysRoleResource>()
                .eq(SysRoleResource::getRoleId, sysRole.getId()));
        roleResourceList.stream().filter(item -> !request.getResourceIds().contains(item.getResourceId())).forEach(item -> {
            sysRoleResourceService.removeById(item);
            request.getResourceIds().remove(item.getResourceId());
        });
        if (!CollectionUtils.isEmpty(request.getResourceIds())) {
            request.getResourceIds().forEach(resourceId -> {
              if (roleResourceList.stream().noneMatch(sysRoleResource -> sysRoleResource.getResourceId().equals(resourceId))) {
                  SysRoleResource roleResource = new SysRoleResource();
                  roleResource.setRoleId(sysRole.getId());
                  roleResource.setResourceId(resourceId);
                  sysRoleResourceService.save(roleResource);
              }
            });
        }
        return sysRole;
    }

    @Transactional
    @Override
    public void deleteRoleAndResources(Long id) {
        this.removeById(id);
        sysUserRoleService.deleteByRoleId(id);
        sysRoleResourceService.deleteByRoleId(id);
    }
}
