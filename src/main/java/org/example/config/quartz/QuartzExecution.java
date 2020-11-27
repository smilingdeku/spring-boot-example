package org.example.config.quartz;

import org.example.common.constant.CommonConstant;
import org.example.common.util.SpringUtil;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.task.ITask;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * @author linzhaoming
 * @since 2020/11/26
 **/
public class QuartzExecution implements Job {

    private static final Logger log = LoggerFactory.getLogger(QuartzExecution.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysScheduleJob job = (SysScheduleJob) context.getMergedJobDataMap().get(CommonConstant.SCHEDULE_JOB_KEY);
        try {
            ITask task = SpringUtil.getBean(job.getBeanName());
            task.execute(job.getParams());
        } catch (Exception e) {
            if (e instanceof NoSuchBeanDefinitionException) {
                log.error("JobId: {}, JobName: {}, Bean: {} not exist", job.getId(), job.getName(), job.getBeanName());
            } else {
                log.error(e.getMessage(), e);
            }
        }
    }

}
