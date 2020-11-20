package org.example.module.system.userrole.mapper;

import org.example.module.system.userrole.domain.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统用户角色关联 Mapper 接口
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 删除用户角色
     *
     * @param userId 用户 ID
     */
    void deleteByUserId(Long userId);

}
