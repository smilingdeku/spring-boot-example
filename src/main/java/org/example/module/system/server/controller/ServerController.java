package org.example.module.system.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.domain.response.Result;
import org.example.common.util.DateUtil;
import org.example.module.system.server.domain.entity.CpuInfo;
import org.example.module.system.server.domain.entity.FileStoreInfo;
import org.example.module.system.server.domain.entity.MemoryInfo;
import org.example.module.system.server.domain.entity.SysInfo;
import org.example.module.system.server.domain.response.ServerResponse;
import org.example.module.system.server.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.util.FormatUtil;

import java.net.UnknownHostException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Api(tags = {"服务器信息接口"})
@RestController
@RequestMapping("/sys/server")
public class ServerController {

    @Autowired
    private IServerService serverService;

    @ApiOperation(value = "获取服务器信息")
    @GetMapping("/info")
    public Result<ServerResponse> info() throws UnknownHostException {
        ServerResponse response = new ServerResponse();

        SysInfo sysInfo = serverService.getSysInfo();
        response.setHostAddress(sysInfo.getHostAddress());
        response.setUptime(DateUtil.secondToTime(sysInfo.getUptime()));
        response.setTime(DateUtil.formatNow("yyyy-MM-dd HH:mm"));

        MemoryInfo memoryInfo = serverService.getMemoryInfo();
        response.setMemory(FormatUtil.formatBytes(memoryInfo.getTotal()));
        response.setMemoryUsage(memoryInfo.getUsage());

        CpuInfo cpuInfo = serverService.getCpuInfo();
        response.setCoreNum(cpuInfo.getCoreNum());
        response.setCpuUsage(cpuInfo.getUsage());

        List<FileStoreInfo> fileStoreInfoList = serverService.getFileStoreInfos();
        Optional<FileStoreInfo> fileStoreInfoOptional = fileStoreInfoList.stream()
                .filter(item -> item.getMount().equals("/")).findFirst();
        long storage = fileStoreInfoOptional.map(FileStoreInfo::getTotal)
                .orElseGet(() -> fileStoreInfoList.stream().mapToLong(FileStoreInfo::getTotal).sum());
        response.setStorage(FormatUtil.formatBytes(storage));
        fileStoreInfoList.stream().max(Comparator.comparingLong(FileStoreInfo::getTotal))
                .ifPresent(item -> response.setMaxSizePartitionUsage(item.getUsage()));

        return Result.success(response);
    }

}
