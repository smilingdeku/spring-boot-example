package org.example.module.system.schedulejob.service.impl;

import org.example.common.base.BaseService;
import org.example.config.quartz.QuartzDisallowConcurrentExecution;
import org.example.config.quartz.QuartzExecution;
import org.example.config.quartz.QuartzManager;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejob.mapper.SysScheduleJobMapper;
import org.example.module.system.schedulejob.service.ISysScheduleJobService;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 * 系统定时任务 服务实现类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-25
 */
@Service
public class SysScheduleJobServiceImpl extends BaseService<SysScheduleJobMapper, SysScheduleJob> implements ISysScheduleJobService {

    private Logger log = LoggerFactory.getLogger(SysScheduleJobServiceImpl.class);

    @Autowired
    private QuartzManager quartzManager;

    @PostConstruct
    private void init() throws SchedulerException {
        quartzManager.getScheduler().clear();
        List<SysScheduleJob> jobList = this.list();
        jobList.forEach(this::createScheduleJob);
    }

    private void createScheduleJob(SysScheduleJob job) {
        Class<? extends Job> jobClass = job.getAllowConcurrent() ? QuartzExecution.class : QuartzDisallowConcurrentExecution.class;
        JobKey jobKey = JobKey.jobKey(Long.toString(job.getId()), job.getGroup());
        TriggerKey triggerKey = TriggerKey.triggerKey(Long.toString(job.getId()), job.getGroup());
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
        try {
            quartzManager.addJob(jobClass, job, jobKey, triggerKey, cronScheduleBuilder);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

}
