package org.example.config.runner;

import org.example.config.quartz.QuartzManager;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejob.service.ISysScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author linzhaoming
 * @since 2020/11/27
 **/
@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private QuartzManager quartzManager;
    @Autowired
    private ISysScheduleJobService scheduleJobService;

    @Override
    public void run(String... args) throws Exception {
        quartzManager.getScheduler().clear();
        List<SysScheduleJob> jobList = scheduleJobService.list();
        if (!CollectionUtils.isEmpty(jobList)) {
            jobList.forEach(scheduleJobService::addJob);
        }
    }

}
