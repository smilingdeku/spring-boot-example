package org.example.common.constant;

/**
 * 通用常量类
 *
 * @author cark
 * @since 2020-11-25
 **/
public class CommonConstant {

    private CommonConstant() {
    }

    /**
     * 最上层资源父级资源 ID
     */
    public static final String TOP_RESOURCE_PARENT_ID = "0";
    /**
     * ScheduleJob Key
     */
    public static final String SCHEDULE_JOB_KEY = "SCHEDULE_JOB";

    /**
     * redis用户信息缓存
     */
    public static final String REDIS_USER_INFO_HASH_KEY = "sys:user:info";

}
