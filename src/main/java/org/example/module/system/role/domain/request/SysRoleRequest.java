package org.example.module.system.role.domain.request;

import java.util.List;

/**
 * @author linzhaoming
 * @since 2020/11/20
 **/
public class SysRoleRequest {

    private Long id;
    private String name;
    private String memo;
    private List<Long> resourceIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<Long> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
