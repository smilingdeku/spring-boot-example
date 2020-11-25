package org.example.module.system.schedulejob.controller;


import org.example.common.base.BaseController;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejob.mapper.SysScheduleJobMapper;
import org.example.module.system.schedulejob.service.impl.SysScheduleJobServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SysScheduleJobController extends BaseController<SysScheduleJobServiceImpl, SysScheduleJobMapper, SysScheduleJob> {

}
