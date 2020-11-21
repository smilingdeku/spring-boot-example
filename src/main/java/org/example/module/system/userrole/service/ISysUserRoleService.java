package org.example.module.system.userrole.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.module.system.userrole.domain.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * 系统用户角色关联 服务类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 删除用户角色
     *
     * @param userId 用户 ID
     */
    void deleteByUserId(Long userId);

    /**
     * 获取用户角色 ID 列表
     *
     * @param userId 用户 ID
     * @return List<Long>
     */
    List<Long> listRoleIdByUserId(Long userId);
}
