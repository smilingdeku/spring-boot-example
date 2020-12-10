package org.example.module.system.server.service;


import org.example.module.system.server.domain.entity.CpuInfo;
import org.example.module.system.server.domain.entity.JvmInfo;
import org.example.module.system.server.domain.entity.MemoryInfo;
import org.example.module.system.server.domain.entity.SysInfo;
import org.example.module.system.server.domain.entity.ThreadInfo;

public interface IServerService {

    /**
     * 获取 CPU 信息
     *
     * @return {@link CpuInfo}
     */
    CpuInfo getCpuInfo();

    /**
     * 获取内存信息
     *
     * @return {@link MemoryInfo}
     */
    MemoryInfo getMemoryInfo();

    /**
     * 获取 JVM 信息
     *
     * @return {@link JvmInfo}
     */
    JvmInfo getJvmInfo();

    /**
     * 获取线程信息
     *
     * @return {@link ThreadInfo}
     */
    ThreadInfo getThreadInfo();

    /**
     * 获取系统信息
     *
     * @return {@link SysInfo}
     */
    SysInfo getSysInfo();

}
