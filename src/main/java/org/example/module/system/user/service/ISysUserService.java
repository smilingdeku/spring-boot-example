package org.example.module.system.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.module.system.user.domain.entity.SysUser;
import org.example.module.system.user.domain.request.SysUserRequest;

import java.util.List;

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
     * @param username 用户名
     * @return SysUser
     */
    SysUser getUserByUsername(String username);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return String
     */
    String login(String username, String password);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser getByUsername(String username);

    /**
     * 根据用户名获取权限列表
     *
     * @param username 用户名
     * @return List<String>
     */
    List<String> listPermissionByUsername(String username);

    /**
     * 保存用户和角色
     *
     * @param request 请求数据
     * @return SysUser
     */
    SysUser saveUserAndRoles(SysUserRequest request);

    /**
     * 更新用户和角色
     *
     * @param request 请求数据
     * @return SysUser
     */
    SysUser updateUserAndRoles(SysUserRequest request);

    /**
     * 删除用户及其角色
     *
     * @param id 用户 ID
     */
    void deleteUserAndRoles(Long id);
}
