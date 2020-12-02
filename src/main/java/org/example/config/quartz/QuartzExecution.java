package org.example.config.quartz;

import org.example.common.constant.CommonConstant;
import org.example.common.util.ConvertUtil;
import org.example.common.util.SpringUtil;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejoblog.service.ISysScheduleJobLogService;
import org.example.task.ITask;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author linzhaoming
 * @since 2020/11/26
 **/
public class QuartzExecution implements Job {

    private static final Logger log = LoggerFactory.getLogger(QuartzExecution.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysScheduleJob job = (SysScheduleJob) context.getMergedJobDataMap().get(CommonConstant.SCHEDULE_JOB_KEY);
        long startTime = System.currentTimeMillis();
        String message = "";
        int spendTime = 0;
        boolean success = false;
        try {
            ITask task = SpringUtil.getBean(job.getBeanName());
            task.execute(job.getParams());
            success = true;
            long endTime = System.currentTimeMillis();
            spendTime = ConvertUtil.toIntValue(endTime - startTime);
        } catch (Exception e) {
            message = e.getMessage();
        } finally {
            ISysScheduleJobLogService logService = SpringUtil.getBean(ISysScheduleJobLogService.class);
            logService.saveLog(job, success, message, spendTime);
        }
    }

}
