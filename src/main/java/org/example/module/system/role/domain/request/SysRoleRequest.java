package org.example.module.system.role.domain.request;

import io.swagger.annotations.ApiModelProperty;
import org.example.common.util.JsonUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author linzhaoming
 * @since 2020/11/20
 **/
public class SysRoleRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "角色名称")
    private String name;
    @ApiModelProperty(value = "备注")
    private String memo;
    @ApiModelProperty(value = "资源 ID 列表")
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

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
