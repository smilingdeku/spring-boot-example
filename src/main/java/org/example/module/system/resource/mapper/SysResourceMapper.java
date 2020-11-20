package org.example.module.system.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.module.system.resource.domain.dto.ResourceTreeNode;
import org.example.module.system.resource.domain.dto.SysResourceDTO;
import org.example.module.system.resource.domain.entity.SysResource;

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

    /**
     * 获取资源节点信息 (无层级关系)
     *
     * @param roleId 角色 ID (获取该角色是否有权限)
     * @return List<ResourceTreeNode>
     */
    List<ResourceTreeNode> listResourceTreeNode(Long roleId);

    /**
     * 获取资源信息
     *
     * @param id ID
     * @return SysResourceDTO
     */
    SysResourceDTO getById(Long id);
}
