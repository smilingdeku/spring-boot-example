package org.example.module.system.resource.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.domain.entity.RouterMeta;
import org.example.common.domain.entity.TreeNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linzhaoming
 * @since 2020-12-25
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterResponse extends TreeNode<RouterResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ApiModelProperty(value = "ID")
    private String id;
    @JsonIgnore
    @ApiModelProperty(value = "父级 ID")
    private String parentId;
    @ApiModelProperty(value = "路径")
    private String path;
    @ApiModelProperty(value = "组件")
    private String component;
    @ApiModelProperty(value = "重定向地址")
    private String redirect;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "属性")
    private RouterMeta meta;
    @ApiModelProperty(value = "总是显示")
    private boolean alwaysShow;
    @ApiModelProperty(value = "是否隐藏")
    private boolean hidden;
    @ApiModelProperty(value = "叶子节点")
    private List<TreeNode<RouterResponse>> children = new ArrayList<>();

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

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RouterMeta getMeta() {
        return meta;
    }

    public void setMeta(RouterMeta meta) {
        this.meta = meta;
    }

    public boolean getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public List<TreeNode<RouterResponse>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<RouterResponse>> children) {
        this.children = children;
    }

    @Override
    public void add(TreeNode<RouterResponse> node) {
        children.add(node);
    }

}
