package org.example.module.system.schedulejoblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.base.BaseController;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.Result;
import org.example.common.util.ConvertUtil;
import org.example.module.system.schedulejoblog.domain.entity.SysScheduleJobLog;
import org.example.module.system.schedulejoblog.mapper.SysScheduleJobLogMapper;
import org.example.module.system.schedulejoblog.service.impl.SysScheduleJobLogServiceImpl;
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
@RestController
@RequestMapping("/sys/schedule-job-log")
public class SysScheduleJobLogController
        extends BaseController<SysScheduleJobLogServiceImpl, SysScheduleJobLogMapper, SysScheduleJobLog> {

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = mapToQuery(requestParam);
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
        IPage<SysScheduleJobLog> page = getBaseService()
                .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        return Result.success(getBaseService().getById(id));
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        boolean success = getBaseService().removeByIds(idList);
        return success ? Result.success() : Result.failure();
    }

}