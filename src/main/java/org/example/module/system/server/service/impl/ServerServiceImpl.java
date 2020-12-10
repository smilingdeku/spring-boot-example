package org.example.module.system.server.service.impl;

import org.example.module.system.server.domain.entity.CpuInfo;
import org.example.module.system.server.domain.entity.JvmInfo;
import org.example.module.system.server.domain.entity.MemoryInfo;
import org.example.module.system.server.domain.entity.SysInfo;
import org.example.module.system.server.domain.entity.ThreadInfo;
import org.example.module.system.server.service.IServerService;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


@Service
public class ServerServiceImpl implements IServerService {

    private static final SystemInfo systemInfo;
    private static final HardwareAbstractionLayer hardware;
    private static final OperatingSystem os;
    public static final Properties props;

    static {
        systemInfo = new SystemInfo();
        hardware = systemInfo.getHardware();
        os = systemInfo.getOperatingSystem();
        props = System.getProperties();
    }

    @Override
    public CpuInfo getCpuInfo() {
        CpuInfo cpuInfo = new CpuInfo();

        CentralProcessor processor = hardware.getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {
            // ignore
        }
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softIrq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long ioWait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        final DecimalFormat format = new DecimalFormat("#.00");

        long totalCpu = Math.max(user + nice + cSys + idle + ioWait + irq + softIrq + steal, 0);

        cpuInfo.setCoreNum(processor.getLogicalProcessorCount());
        cpuInfo.setSysUsage(Double.parseDouble(format.format(cSys <= 0 ? 0 : (100d * cSys / totalCpu))));
        cpuInfo.setUserUsage(Double.parseDouble(format.format(user <= 0 ? 0 : (100d * user / totalCpu))));
        cpuInfo.setIoWaitRate(totalCpu == 0 ? 0 : Double.parseDouble(format.format(100d * ioWait / totalCpu)));
        cpuInfo.setFreeRate(Double.parseDouble(format.format(idle <= 0 ? 0 : (100d * idle / totalCpu))));

        return cpuInfo;
    }

    @Override
    public MemoryInfo getMemoryInfo() {
        MemoryInfo memoryInfo = new MemoryInfo();

        GlobalMemory memory = hardware.getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;
        final DecimalFormat format = new DecimalFormat("#.00");

        memoryInfo.setTotal(total);
        memoryInfo.setAvailable(available);
        memoryInfo.setUsed(total - available);
        memoryInfo.setUsage(Double.parseDouble(format.format(used <= 0 ? 0 : (100d * used / total))));

        return memoryInfo;
    }

    @Override
    public JvmInfo getJvmInfo() {
        JvmInfo jvmInfo = new JvmInfo();

        Runtime runtime = Runtime.getRuntime();

        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long used = total - free;
        final DecimalFormat format = new DecimalFormat("#.00");

        jvmInfo.setJdkVersion(props.getProperty("java.version"));
        jvmInfo.setTotal(total);
        jvmInfo.setAvailable(free);
        jvmInfo.setUsed(used);
        jvmInfo.setUsage(Double.parseDouble(format.format(used <= 0 ? 0 : (100d * used / total))));

        return jvmInfo;
    }

    @Override
    public ThreadInfo getThreadInfo() {
        ThreadInfo threadInfo = new ThreadInfo();

        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        while (Objects.nonNull(currentGroup.getParent())) {
            currentGroup = currentGroup.getParent();
        }
        threadInfo.setActiveCount(currentGroup.activeCount());

        return threadInfo;
    }

    @Override
    public SysInfo getSysInfo() {
        SysInfo sysInfo = new SysInfo();

        sysInfo.setOsName(props.getProperty("os.name"));
        sysInfo.setOsArch(props.getProperty("os.arch"));

        return sysInfo;
    }

}
