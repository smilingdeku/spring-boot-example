package org.example.module.system.server.domain.entity;

import org.example.common.util.JsonUtil;

/**
 * JVM 信息
 */
public class JvmInfo {

    /**
     * 总内存
     */
    private long total;
    /**
     * 剩余内存
     */
    private long available;
    /**
     * 使用内存
     */
    private long used;
    /**
     * 内存使用率
     */
    private double usage;
    /**
     * JDK 版本
     */
    private String jdkVersion;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
