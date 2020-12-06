package org.example.module.system.schedulejoblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.base.BaseService;
import org.example.common.util.ConvertUtil;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejoblog.domain.entity.SysScheduleJobLog;
import org.example.module.system.schedulejoblog.mapper.SysScheduleJobLogMapper;
import org.example.module.system.schedulejoblog.service.ISysScheduleJobLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统定时任务执行日志 服务实现类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-30
 */
@Service
public class SysScheduleJobLogServiceImpl extends BaseService<SysScheduleJobLogMapper, SysScheduleJobLog> implements ISysScheduleJobLogService {

    @Override
    public void saveLog(SysScheduleJob job, boolean success, String message, Integer spendTime) {
        SysScheduleJobLog log = new SysScheduleJobLog();
        log.setJobId(job.getId());
        log.setJobName(job.getName());
        log.setBeanName(job.getBeanName());
        log.setParams(job.getParams());
        log.setSpendTime(spendTime);
        log.setMessage(message);
        log.setStatus(ConvertUtil.toInteger(success));
        this.save(log);
    }

    @Override
    public boolean deleteByJobId(Long jobId) {
        return this.remove(new LambdaQueryWrapper<SysScheduleJobLog>().eq(SysScheduleJobLog::getJobId, jobId));
    }
}
