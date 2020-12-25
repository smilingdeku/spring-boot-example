package org.example.module.system.schedulejoblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.annotation.Log;
import org.example.common.base.BaseController;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.PageResult;
import org.example.common.domain.response.Result;
import org.example.common.util.ConvertUtil;
import org.example.common.util.MapperUtil;
import org.example.module.system.schedulejoblog.domain.entity.SysScheduleJobLog;
import org.example.module.system.schedulejoblog.domain.response.SysScheduleJobLogResponse;
import org.example.module.system.schedulejoblog.mapper.SysScheduleJobLogMapper;
import org.example.module.system.schedulejoblog.service.impl.SysScheduleJobLogServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统定时任务执行日志 前端控制器
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-30
 */
@Api(tags = {"调度日志接口"})
@RestController
@RequestMapping("/sys/schedule-job-log")
public class SysScheduleJobLogController
        extends BaseController<SysScheduleJobLogServiceImpl, SysScheduleJobLogMapper, SysScheduleJobLog> {

    @ApiOperation(value = "获取调度日志分页数据")
    @PreAuthorize("hasAuthority('monitor:schedule-job-log')")
    @GetMapping("/page")
    public PageResult<SysScheduleJobLogResponse> page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = QueryRequest.from(requestParam);
        QueryWrapper<SysScheduleJobLog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            queryWrapper.lambda().like(SysScheduleJobLog::getJobName, query.getKeyword());
        }
        String beanName = ConvertUtil.toStr(query.get("beanName"), null);
        if (!StringUtils.isEmpty(beanName)) {
            queryWrapper.lambda().like(SysScheduleJobLog::getBeanName, beanName);
        }
        if (!StringUtils.isEmpty(query.getLineOrderField())) {
            queryWrapper.orderBy(true, query.getIsAsc(), query.getLineOrderField());
        }
        IPage<SysScheduleJobLog> page = getService()
                .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        List<SysScheduleJobLogResponse> responseList = MapperUtil.mapList(page.getRecords(), SysScheduleJobLog.class, SysScheduleJobLogResponse.class);
        return PageResult.build(responseList, page.getTotal());
    }

    @ApiOperation(value = "删除调度日志")
    @PreAuthorize("hasAuthority('monitor:schedule-job-log')")
    @GetMapping("/{id}")
    public Result<SysScheduleJobLogResponse> get(@PathVariable Long id) {
        SysScheduleJobLog sysScheduleJobLog = getService().getById(id);
        SysScheduleJobLogResponse response = MapperUtil.map(sysScheduleJobLog, SysScheduleJobLogResponse.class);
        return Result.success(response);
    }

    @Log
    @PreAuthorize("hasAuthority('monitor:schedule-job-log:delete')")
    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        boolean success = getService().removeByIds(idList);
        return success ? Result.success() : Result.failure();
    }

}
