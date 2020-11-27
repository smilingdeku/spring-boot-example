package org.example.module.system.schedulejob.enums;

/**
 * @author linzhaoming
 * @since 2020/11/27
 **/
public enum ScheduleJobMisfirePolicy {

    DEFAULT(0),
    DO_NOTHING(1),
    IGNORE_MISFIRES(2),
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
