package org.example.module.system.server.service;


import org.example.module.system.server.domain.entity.CpuInfo;
import org.example.module.system.server.domain.entity.JvmInfo;
import org.example.module.system.server.domain.entity.MemoryInfo;

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

}
