package org.example.module.system.resource.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.common.domain.entity.TreeNode;

import java.io.Serializable;

/**
 * @author linzhaoming
 * @since 2020/11/18
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourceTreeNode extends TreeNode<ResourceTreeNode> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private boolean hasPermission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
}
