package org.example.module.system.user.service;

import org.example.module.system.user.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-10-23
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过用户名获取用户
     *
     * @param userName 用户名
     * @return SysUser
     */
    SysUser getUserByUserName(String userName);
}
