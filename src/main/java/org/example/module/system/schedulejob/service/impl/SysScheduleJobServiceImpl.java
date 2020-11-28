package org.example.module.system.schedulejob.service.impl;

import org.example.common.base.BaseService;
import org.example.common.constant.CommonConstant;
import org.example.common.constant.MsgKeyConstant;
import org.example.common.exception.BusinessException;
import org.example.common.util.MessageUtil;
import org.example.config.quartz.QuartzDisallowConcurrentExecution;
import org.example.config.quartz.QuartzExecution;
import org.example.config.quartz.QuartzManager;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejob.enums.ScheduleJobMisfirePolicy;
import org.example.module.system.schedulejob.enums.ScheduleJobStatus;
import org.example.module.system.schedulejob.mapper.SysScheduleJobMapper;
import org.example.module.system.schedulejob.service.ISysScheduleJobService;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

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

    @Override
    public void addJob(SysScheduleJob job) throws SchedulerException {
        Class<? extends Job> jobClass = job.getAllowConcurrent() ? QuartzExecution.class : QuartzDisallowConcurrentExecution.class;
        JobKey jobKey = JobKey.jobKey(Long.toString(job.getId()), job.getGroup());
        TriggerKey triggerKey = TriggerKey.triggerKey(Long.toString(job.getId()), job.getGroup());
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
        ScheduleJobMisfirePolicy misfirePolicy = ScheduleJobMisfirePolicy.toMisFirePolicy(job.getMisfirePolicy());
        if (Objects.nonNull(misfirePolicy)) {
            this.handleMisfirePolicy(cronScheduleBuilder, misfirePolicy);
        }
        quartzManager.addJob(jobClass, job, jobKey, triggerKey, cronScheduleBuilder);
    }

    @Override
    public void initJobs() throws SchedulerException {
        quartzManager.getScheduler().clear();
        List<SysScheduleJob> jobList = this.list();
        if (!CollectionUtils.isEmpty(jobList)) {
            for (SysScheduleJob job : jobList) {
                this.addJob(job);
            }
        }
    }

    @Transactional
    @Override
    public boolean saveJob(SysScheduleJob job) throws SchedulerException {
        this.checkCronExpression(job.getCron());
        boolean success = this.save(job);
        if (success) {
            this.addJob(job);
        }
        return success;
    }

    @Transactional
    @Override
    public boolean updateJob(SysScheduleJob job) throws SchedulerException {
        this.checkCronExpression(job.getCron());
        boolean success = this.updateById(job);
        if (success) {
            JobKey jobKey = JobKey.jobKey(Long.toString(job.getId()), job.getGroup());
            if (quartzManager.getScheduler().checkExists(jobKey)) {
                quartzManager.getScheduler().deleteJob(jobKey);
            }
            this.addJob(job);
        }
        return success;
    }

    @Transactional
    @Override
    public void deleteJob(Long id) throws SchedulerException {
        SysScheduleJob job = this.getById(id);
        if (Objects.nonNull(job)) {
            boolean success = this.removeById(job.getId());
            if (success) {
                JobKey jobKey = JobKey.jobKey(Long.toString(job.getId()), job.getGroup());
                quartzManager.getScheduler().deleteJob(jobKey);
            }
        }
    }

    @Transactional
    @Override
    public void deleteJobByIdList(List<Long> idList) throws SchedulerException {
        for (Long id : idList) {
            this.deleteJob(id);
        }
    }

    @Override
    public void runJob(Long id) throws SchedulerException {
        SysScheduleJob job = this.getById(id);
        if (Objects.nonNull(job)) {
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(CommonConstant.SCHEDULE_JOB_KEY, job);
            JobKey jobKey = JobKey.jobKey(Long.toString(job.getId()), job.getGroup());
            quartzManager.getScheduler().triggerJob(jobKey, dataMap);
        }
    }

    @Transactional
    @Override
    public void pauseJob(Long id) throws SchedulerException {
        SysScheduleJob job = this.getById(id);
        if (Objects.nonNull(job)) {
            job.setStatus(ScheduleJobStatus.DISABLE.getStatus());
            this.updateById(job);
            JobKey jobKey = JobKey.jobKey(Long.toString(job.getId()), job.getGroup());
            quartzManager.getScheduler().pauseJob(jobKey);
        }
    }

    @Transactional
    @Override
    public void resumeJob(Long id) throws SchedulerException {
        SysScheduleJob job = this.getById(id);
        if (Objects.nonNull(job)) {
            job.setStatus(ScheduleJobStatus.DISABLE.getStatus());
            this.updateById(job);
            JobKey jobKey = JobKey.jobKey(Long.toString(job.getId()), job.getGroup());
            quartzManager.getScheduler().resumeJob(jobKey);
        }
    }

    private void handleMisfirePolicy(CronScheduleBuilder cronScheduleBuilder, ScheduleJobMisfirePolicy policy) {
        switch (policy) {
            case DO_NOTHING:
                cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
                break;
            case IGNORE_MISFIRES:
                cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
                break;
            case FIRE_AND_PROCEED:
                cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
                break;
            case DEFAULT:
            default:
                break;
        }
    }

    private void checkCronExpression(String cron) {
        if (!CronExpression.isValidExpression(cron)) {
            String msg = MessageUtil.message(MsgKeyConstant.QUARTZ_JOB_INVALID_CRON, cron);
            throw new BusinessException(msg);
        }
    }

}
