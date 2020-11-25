package org.example.module.system.userrole.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.module.system.userrole.domain.entity.SysUserRole;

import java.util.List;

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
     * 获取用户角色 ID 列表
     *
     * @param userId 用户 ID
     * @return List<Long>
     */
    List<Long> listRoleIdByUserId(Long userId);

}
