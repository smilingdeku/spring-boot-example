package org.example.module.system.schedulejoblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.example.module.system.schedulejoblog.domain.entity.SysScheduleJobLog;

/**
 * <p>
 * 系统定时任务执行日志 服务类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-30
 */
public interface ISysScheduleJobLogService extends IService<SysScheduleJobLog> {

    /**
     * 保存任务执行日志
     *
     * @param job       定时任务
     * @param success   是否成功
     * @param message   消息记录
     * @param spendTime 耗时
     */
    void saveLog(SysScheduleJob job, boolean success, String message, Integer spendTime);

    /**
     * 根据任务 ID 删除调度日志
     *
     * @param jobId 任务 ID
     * @return boolean
     */
    boolean deleteByJobId(Long jobId);

}
