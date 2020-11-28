package org.example.config.runner;

import org.example.module.system.schedulejob.service.ISysScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author linzhaoming
 * @since 2020/11/27
 **/
@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private ISysScheduleJobService scheduleJobService;

    @Override
    public void run(String... args) throws Exception {
        scheduleJobService.initJobs();
    }

}
