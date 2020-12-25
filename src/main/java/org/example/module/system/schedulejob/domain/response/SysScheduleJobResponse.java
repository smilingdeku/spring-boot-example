package org.example.module.system.schedulejob.domain.response;

import io.swagger.annotations.ApiModelProperty;
import org.example.common.util.JsonUtil;

import java.io.Serializable;
import java.time.LocalDateTime;


public class SysScheduleJobResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "任务名称")
    private String name;
    @ApiModelProperty(value = "任务组")
    private String group;
    @ApiModelProperty(value = "bean 名称")
    private String beanName;
    @ApiModelProperty(value = "参数")
    private String params;
    @ApiModelProperty(value = "cron 表达式")
    private String cron;
    @ApiModelProperty(value = "允许并发 [0-不允许] [1-允许]")
    private Boolean allowConcurrent;
    @ApiModelProperty(value = "超时策略 [0-默认] [1-不触发] [2-立刻触发] [3-立刻触发一次]")
    private Integer misfirePolicy;
    @ApiModelProperty(value = "备注")
    private String memo;
    @ApiModelProperty(value = "状态 [0-禁用] [1-启用]")
    private Integer status;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Boolean getAllowConcurrent() {
        return allowConcurrent;
    }

    public void setAllowConcurrent(Boolean allowConcurrent) {
        this.allowConcurrent = allowConcurrent;
    }

    public Integer getMisfirePolicy() {
        return misfirePolicy;
    }

    public void setMisfirePolicy(Integer misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return JsonUtil.toJSONString(this);
    }
}
