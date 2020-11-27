package org.example.module.system.schedulejob.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.module.system.schedulejob.domain.entity.SysScheduleJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * <p>
 * 系统定时任务 服务类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-25
 */
public interface ISysScheduleJobService extends IService<SysScheduleJob> {

    /**
     * 添加定时任务
     *
     * @param job 定时任务
     */
    void addJob(SysScheduleJob job);

    /**
     * 保存定时任务
     *
     * @param job 定时任务
     * @return boolean
     */
    boolean saveJob(SysScheduleJob job);

    /**
     * 更新定时任务
     *
     * @param job 定时任务
     * @return boolean
     * @throws SchedulerException
     */
    boolean updateJob(SysScheduleJob job) throws SchedulerException;

    /**
     * 删除任务
     *
     * @param id 任务 ID
     * @throws SchedulerException
     */
    void deleteJob(Long id) throws SchedulerException;

    /**
     * 批量删除任务
     *
     * @param idList 任务 ID 列表
     * @throws SchedulerException
     */
    void deleteJobByIdList(List<Long> idList) throws SchedulerException;

    /**
     * 执行任务
     *
     * @param id 任务 ID
     * @throws SchedulerException
     */
    void runJob(Long id) throws SchedulerException;

    /**
     * 暂停任务
     *
     * @param id 任务 ID
     * @throws SchedulerException
     */
    void pauseJob(Long id) throws SchedulerException;

    /**
     * 恢复任务
     *
     * @param id 任务 ID
     * @throws SchedulerException
     */
    void resumeJob(Long id) throws SchedulerException;

}
