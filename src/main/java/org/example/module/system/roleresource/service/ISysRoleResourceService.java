package org.example.module.system.roleresource.service;

import org.example.module.system.roleresource.domain.entity.SysRoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统角色资源关联 服务类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
public interface ISysRoleResourceService extends IService<SysRoleResource> {

    /**
     * 根据角色 ID 删除资源
     *
     * @param roleId 角色 ID
     */
    void deleteByRoleId(Long roleId);

}
