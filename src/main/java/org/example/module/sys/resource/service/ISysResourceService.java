package org.example.module.sys.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.domain.Router;
import org.example.module.sys.resource.domain.entity.SysResource;

import java.util.List;

/**
 * <p>
 * 系统资源 服务类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
public interface ISysResourceService extends IService<SysResource> {

    /**
     * 根据用户名和资源类型获取资源
     *
     * @param username 用户名
     * @param type     类型
     * @return List<SysResource>
     */
    List<SysResource> listByUsernameAndType(String username, int type);

    /**
     * 根据用户名获取路由
     *
     * @param username 用户名
     * @return List<Router>
     */
    List<Router> listRouterByUsername(String username);
}
