package org.example.module.system.schedulejob.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.annotation.Log;
import org.example.common.base.BaseController;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.PageResult;
import org.example.common.domain.response.Result;
import org.example.common.util.ConvertUtil;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejob.mapper.SysScheduleJobMapper;
import org.example.module.system.schedulejob.service.impl.SysScheduleJobServiceImpl;
import org.quartz.SchedulerException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统定时任务 前端控制器
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/sys/schedule-job")
public class SysScheduleJobController
    extends BaseController<SysScheduleJobServiceImpl, SysScheduleJobMapper, SysScheduleJob> {

    @PreAuthorize("hasAuthority('monitor:schedule-job')")
    @GetMapping("/page")
    public PageResult<SysScheduleJob> page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = QueryRequest.from(requestParam);
        QueryWrapper<SysScheduleJob> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            queryWrapper.lambda().like(SysScheduleJob::getName, query.getKeyword());
        }
        String beanName = ConvertUtil.toStr(query.get("beanName"), null);
        if (!StringUtils.isEmpty(beanName)) {
            queryWrapper.lambda().like(SysScheduleJob::getBeanName, beanName);
        }
        if (!StringUtils.isEmpty(query.getLineOrderField())) {
            queryWrapper.orderBy(true, query.getIsAsc(), query.getLineOrderField());
        }
        IPage<SysScheduleJob> page = getService()
            .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        return PageResult.build(page);
    }

    @PreAuthorize("hasAuthority('monitor:schedule-job:edit')")
    @GetMapping("/{id}")
    public Result<SysScheduleJob> get(@PathVariable Long id) {
        return Result.success(getService().getById(id));
    }

    @Log
    @PreAuthorize("hasAuthority('monitor:schedule-job:run')")
    @GetMapping("/{id}/run")
    public Result<Void> run(@PathVariable Long id) throws SchedulerException {
        getService().runJob(id);
        return Result.success();
    }

    @Log
    @PreAuthorize("hasAuthority('monitor:schedule-job:add')")
    @PostMapping
    public Result<SysScheduleJob> save(@RequestBody SysScheduleJob sysScheduleJob) throws SchedulerException {
        // 检查任务是否存在
        // checkJobArgs(sysScheduleJob);
        boolean success = getService().saveJob(sysScheduleJob);
        return success ? Result.success(sysScheduleJob) : Result.failure();
    }

    @Log
    @PreAuthorize("hasAuthority('monitor:schedule-job:delete')")
    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable Long[] ids) throws SchedulerException {
        List<Long> idList = Arrays.asList(ids);
        getService().deleteJobByIdList(idList);
        return Result.success();
    }

    @Log
    @PreAuthorize("hasAuthority('monitor:schedule-job:edit')")
    @PutMapping
    public Result<SysScheduleJob> update(@RequestBody SysScheduleJob sysScheduleJob) throws SchedulerException {
        // 检查任务是否存在
        // checkJobArgs(sysScheduleJob);
        boolean success = getService().updateJob(sysScheduleJob);
        return success ? Result.success(sysScheduleJob) : Result.failure();
    }

//    /**
//     * 检查任务是否合法
//     *
//     * @param jobInfo 定时任务信息
//     */
//    private void checkJobArgs(SysScheduleJob jobInfo) {
//        try {
//            SpringUtil.getBean(jobInfo.getBeanName());
//        } catch (Exception e) {
//            String message = MessageUtil.message(MsgKeyConstant.QUARTZ_JOB_NOT_EXISTS, jobInfo.getBeanName());
//            throw new BusinessException(message);
//        }
//    }

}
