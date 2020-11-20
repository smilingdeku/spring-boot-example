package org.example.module.system.role.service;

import org.example.module.system.role.domain.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.module.system.role.domain.request.SysRoleRequest;

/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-10-26
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 保存角色以及角色资源
     *
     * @param request 请求数据
     * @return SysRole
     */
    SysRole saveRoleAndResources(SysRoleRequest request);

    /**
     * 更新角色以及角色资源
     *
     * @param request 请求数据
     * @return SysRole
     */
    SysRole updateRoleAndResources(SysRoleRequest request);

}
