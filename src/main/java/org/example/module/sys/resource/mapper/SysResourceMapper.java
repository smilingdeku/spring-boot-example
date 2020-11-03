package org.example.module.sys.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.module.sys.resource.domain.entity.SysResource;

import java.util.List;

/**
 * <p>
 * 系统资源 Mapper 接口
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    /**
     * 获取用户资源
     *
     * @param username 用户名
     * @param type     资源类型
     * @return List<SysResource>
     */
    List<SysResource> listByUsernameAndType(String username, int type);

}
