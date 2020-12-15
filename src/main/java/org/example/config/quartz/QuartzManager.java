package org.example.config.quartz;

import org.example.common.constant.CommonConstant;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejob.enums.ScheduleJobStatus;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author linzhaoming
 * @since 2020/11/26
 **/
@Component
public class QuartzManager {

    @Autowired
    private Scheduler scheduler;

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void addJob(Class<? extends Job> jobClass, SysScheduleJob job,
                       JobKey jobKey, TriggerKey triggerKey, CronScheduleBuilder cronScheduleBuilder) throws SchedulerException {
        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).build();
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder).build();
        jobDetail.getJobDataMap().put(CommonConstant.SCHEDULE_JOB_KEY, job);
        scheduler.scheduleJob(jobDetail, trigger);
        if (ScheduleJobStatus.DISABLE.getStatus().equals(job.getStatus())) {
            scheduler.pauseJob(jobKey);
        }
    }

}
