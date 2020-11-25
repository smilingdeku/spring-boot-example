package org.example.module.system.userrole.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.base.BaseService;
import org.example.module.system.userrole.domain.entity.SysUserRole;
import org.example.module.system.userrole.mapper.SysUserRoleMapper;
import org.example.module.system.userrole.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean deleteByUserId(Long userId) {
        return this.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
    }

    @Override
    public boolean deleteByRoleId(Long roleId) {
        return this.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, roleId));
    }

    @Override
    public List<Long> listRoleIdByUserId(Long userId) {
        return getBaseMapper().listRoleIdByUserId(userId);
    }
}
