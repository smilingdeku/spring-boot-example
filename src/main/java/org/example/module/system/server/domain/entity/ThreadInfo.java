package org.example.module.system.server.domain.entity;

import org.example.common.util.JsonUtil;

/**
 * 线程信息
 */
public class ThreadInfo {

    /**
     * 活跃数
     */
    private long activeCount;

    public long getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(long activeCount) {
        this.activeCount = activeCount;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
