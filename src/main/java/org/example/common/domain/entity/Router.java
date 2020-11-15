package org.example.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author linzhaoming
 * @since 2020/11/03
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Router extends TreeNode<Router> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String path;
    private String component;
    private String redirect;
    private String name;
    private RouterMeta meta;
    private boolean alwaysShow;
    private boolean hidden;

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
}
