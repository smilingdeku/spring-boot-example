package org.example.module.system.resource.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.domain.entity.Router;
import org.example.module.system.resource.domain.dto.ResourceTreeNode;
import org.example.module.system.resource.domain.entity.SysResource;

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

    /**
     * 获取资源节点信息 (有层级关系)
     *
     * @param roleId 角色 ID (获取该角色是否有权限)
     * @return List<ResourceTreeNode>
     */
    List<ResourceTreeNode> listResourceTreeNode(Long roleId);

    /**
     * 获取资源节点列表 (有层级关系)
     *
     * @param queryWrapper 查询条件
     * @return List<ResourceTreeNode>
     */
    List<ResourceTreeNode> listResourceTreeNode(Wrapper<SysResource> queryWrapper);
}
