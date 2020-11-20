package org.example.module.system.userrole.service.impl;

import org.example.common.base.BaseService;
import org.example.module.system.userrole.domain.entity.SysUserRole;
import org.example.module.system.userrole.mapper.SysUserRoleMapper;
import org.example.module.system.userrole.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户角色关联 服务实现类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
@Service
public class SysUserRoleServiceImpl extends BaseService<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public void deleteByUserId(Long userId) {
        getBaseMapper().deleteByUserId(userId);
    }
}
