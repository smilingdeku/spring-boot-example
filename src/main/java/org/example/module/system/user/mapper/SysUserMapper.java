package org.example.module.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.module.system.user.domain.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author linzhaoming
 * @since 2020-10-23
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名获取权限列表
     *
     * @param username 用户名
     * @return List<String>
     */
    List<String> listPermissionByUsername(String username);
}
