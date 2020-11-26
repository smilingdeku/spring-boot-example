package org.example.config.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author linzhaoming
 * @since 2020/11/26
 **/
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends QuartzExecution {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        super.execute(context);
    }
}
