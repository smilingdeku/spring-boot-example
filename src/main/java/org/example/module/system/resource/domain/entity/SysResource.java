package org.example.module.system.resource.domain.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
 * <p>
 * 系统资源
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
@TableName("t_sys_resource")
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 类型常量 */
    public static final int TYPE_MENU = 1;
    public static final int TYPE_BUTTON = 2;

    /**
     * 主键
     */
    private Long id;

    /**
     * 父级 ID
     */
    private Long parentId;

    /**
     * 类型 [1-菜单] [2-按钮]
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 权限标志
     */
    private String permission;

    /**
     * 排序号
     */
    private Integer sortNumber;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SysResource{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", type=" + type +
        ", name=" + name +
        ", icon=" + icon +
        ", path=" + path +
        ", component=" + component +
        ", permission=" + permission +
        ", sortNumber=" + sortNumber +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        "}";
    }
}
