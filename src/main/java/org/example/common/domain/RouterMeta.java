package org.example.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author linzhaoming
 * @since 2020/11/03
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String icon;
    private Boolean breadcrumb = true;

    public RouterMeta() {
    }

    public RouterMeta(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public RouterMeta(String title, String icon, Boolean breadcrumb) {
        this.title = title;
        this.icon = icon;
        this.breadcrumb = breadcrumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(Boolean breadcrumb) {
        this.breadcrumb = breadcrumb;
    }
}
