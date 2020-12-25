package org.example.module.system.resource.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.domain.entity.TreeNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linzhaoming
 * @since 2020-12-25
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourceTreeNodeResponse extends TreeNode<ResourceTreeNodeResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;
    @ApiModelProperty(value = "父级 ID")
    private String parentId;
    @ApiModelProperty(value = "类型 [1-菜单] [2-按钮]")
    private Integer type;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "路径")
    private String path;
    @ApiModelProperty(value = "组件")
    private String component;
    @ApiModelProperty(value = "权限标志")
    private String permission;
    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;
    @ApiModelProperty(value = "是否有权限")
    private Boolean hasPermission;
    @ApiModelProperty(value = "叶子节点")
    private List<TreeNode<ResourceTreeNodeResponse>> children = new ArrayList<>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

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

    @Override
    public List<TreeNode<ResourceTreeNodeResponse>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<ResourceTreeNodeResponse>> children) {
        this.children = children;
    }

    @Override
    public void add(TreeNode<ResourceTreeNodeResponse> node) {
        children.add(node);
    }
}
