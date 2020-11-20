package org.example.module.system.roleresource.mapper;

import org.example.module.system.roleresource.domain.entity.SysRoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统角色资源关联 Mapper 接口
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    /**
     * 根据角色 ID 删除资源
     *
     * @param roleId 角色 ID
     */
    void deleteByRoleId(Long roleId);
}
