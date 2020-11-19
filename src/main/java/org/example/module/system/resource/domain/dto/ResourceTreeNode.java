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

    private Integer type;
    private String name;
    private String icon;
    private String path;
    private String component;
    private String permission;
    private Integer sortNumber;
    private Boolean hasPermission;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Boolean getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(Boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
}
