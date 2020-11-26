package org.example.module.system.schedulejob.enums;

/**
 * @author linzhaoming
 * @since 2020/11/26
 **/
public enum ScheduleJobStatus {
    /**
     * 启用
     */
    DISABLE(0),
    /**
     * 禁用
     */
    ENABLE(1);

    private final Integer status;

    ScheduleJobStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
