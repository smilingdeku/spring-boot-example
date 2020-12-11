package org.example.module.system.server.service.impl;

import com.google.common.collect.Lists;
import org.example.module.system.server.domain.entity.CpuInfo;
import org.example.module.system.server.domain.entity.FileStoreInfo;
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
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


@Service
public class ServerServiceImpl implements IServerService {

    private static final SystemInfo SYSTEM_INFO;
    private static final HardwareAbstractionLayer HARDWARE;
    private static final OperatingSystem OS;
    private static final Properties PROPS;
    private static final FileSystem FS;

    static {
        SYSTEM_INFO = new SystemInfo();
        HARDWARE = SYSTEM_INFO.getHardware();
        OS = SYSTEM_INFO.getOperatingSystem();
        PROPS = System.getProperties();
        FS = OS.getFileSystem();
    }

    @Override
    public CpuInfo getCpuInfo() {
        CpuInfo cpuInfo = new CpuInfo();

        CentralProcessor processor = HARDWARE.getProcessor();
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

        GlobalMemory memory = HARDWARE.getMemory();
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

        jvmInfo.setJdkVersion(PROPS.getProperty("java.version"));
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
    public SysInfo getSysInfo() throws UnknownHostException {
        SysInfo sysInfo = new SysInfo();

        sysInfo.setOsName(PROPS.getProperty("os.name"));
        sysInfo.setOsArch(PROPS.getProperty("os.arch"));
        sysInfo.setUptime(HARDWARE.getProcessor().getSystemUptime());
        sysInfo.setHostName(InetAddress.getLocalHost().getHostName());
        sysInfo.setHostAddress(InetAddress.getLocalHost().getHostAddress());

        return sysInfo;
    }

    @Override
    public List<FileStoreInfo> getFileStoreInfos() {
        OSFileStore[] fileStores = FS.getFileStores();
        List<FileStoreInfo> fileStoreInfoList = Lists.newArrayListWithExpectedSize(fileStores.length);
        for (OSFileStore fileStore : fileStores) {
            FileStoreInfo fileStoreInfo = new FileStoreInfo();
            fileStoreInfo.setMount(fileStore.getMount());
            fileStoreInfo.setType(fileStore.getType());
            fileStoreInfo.setName(fileStore.getName());
            fileStoreInfo.setTotal(fileStore.getTotalSpace());
            fileStoreInfo.setAvailable(fileStore.getUsableSpace());
            fileStoreInfo.setUsed(fileStore.getTotalSpace() - fileStore.getUsableSpace());
            fileStoreInfoList.add(fileStoreInfo);
        }
        return fileStoreInfoList;
    }

}
