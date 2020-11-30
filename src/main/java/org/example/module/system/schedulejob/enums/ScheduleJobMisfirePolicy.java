package org.example.module.system.schedulejob.enums;

/**
 * 任务触发超时执行策略
 *
 * 有如下情况可能导致任务触发超时：
 * 1）系统重启，在关闭到重启的一段时间，任务无法正常触发执行
 * 2）任务被暂停的一段时间里，有些任务无法被触发执行
 * 3）任务不允许并发，在下一次触发时间到达时，上一次执行还没结束
 *
 * misfireThreshold 为触发器超时的临界值，默认为 1 minute，若超过这个阈值，则认为超时，否则为不超时
 *
 * @author linzhaoming
 * @since 2020/11/27
 **/
public enum ScheduleJobMisfirePolicy {

    DEFAULT(0),
    /**
     *忽略 misfire 策略，不触发发生 misfire 的任务
     */
    DO_NOTHING(1),
    /**
     * 立刻触发发生 misfire 的任务
     */
    IGNORE_MISFIRES(2),
    /**
     * 立刻触发一次发生 misfire 的任务
     */
    FIRE_AND_PROCEED(3),
    ;

    private final Integer misfirePolicy;

    ScheduleJobMisfirePolicy(Integer misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    public Integer getMisfirePolicy() {
        return misfirePolicy;
    }

    public static ScheduleJobMisfirePolicy toMisFirePolicy(Integer value) {
        for (ScheduleJobMisfirePolicy policy : ScheduleJobMisfirePolicy.values()) {
            if (policy.getMisfirePolicy().equals(value)) {
                return policy;
            }
        }
        return null;
    }

}
