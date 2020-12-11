package org.example.module.system.server.domain.entity;

import org.example.common.util.JsonUtil;

/**
 * 系统信息
 */
public class SysInfo {

    /**
     * 操作系统名
     */
    private String osName;

    /**
     * 操作系统架构
     */
    private String osArch;
    /**
     * 主机名称
     */
    private String hostName;
    /**
     * 主机地址
     */
    private String hostAddress;
    /**
     * 运行时间
     */
    private long uptime;

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
