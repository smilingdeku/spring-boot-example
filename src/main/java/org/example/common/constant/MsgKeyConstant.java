package org.example.common.constant;

public class MsgKeyConstant {

    private MsgKeyConstant() {
    }

    /**
     * 请求成功
     */
    public static final String SUCCESS = "success";
    /**
     * 操作失败
     */
    public static final String FAIL = "failure";
    /**
     * 认证失败
     */
    public static final String UNAUTHORIZED = "unauthorized";
    /**
     * 权限不足
     */
    public static final String PERMISSION_DENIED = "permission.denied";
    /**
     * 请求方式不支持
     */
    public static final String REQUEST_METHOD_NOT_SUPPORTED = "request-method.not-supported";
    /**
     * 请求参数不存在
     */
    public static final String REQUEST_PARAM_NOT_EXISTED = "request-param.not-existed";
    /**
     * 用户不存在
     */
    public static final String SYSTEM_USER_NOT_EXISTED = "system.user.not-existed";
    /**
     * 用户已被删除
     */
    public static final String SYSTEM_USER_IS_DELETED = "system.user.deleted";
    /**
     * 用户已被禁用
     */
    public static final String SYSTEM_USER_IS_DISABLE = "system.user.disable";
    /**
     * 用户名与密码不匹配
     */
    public static final String SYSTEM_USERNAME_PASSWORD_NOT_MATCH = "system.user.password-not-match";
    /**
     * 系统用户已经存在
     */
    public static final String SYSTEM_USER_ALREADY_EXISTS = "system.user.already-exists";
    /**
     * 定时任务不存在
     */
    public static final String QUARTZ_JOB_NOT_EXISTS = "quartz.job.not-exists";
    /**
     * 无效 Cron 表达式
     */
    public static final String QUARTZ_JOB_INVALID_CRON = "quartz.job.invalid-cron";
    /**
     * rpc请求失败
     */
    public static final String RPC_FAILURE = "rpc.failure";
    /**
     * 验证码类型不存在
     */
    public static final String CAPTCHA_TYPE_NOT_EXISTED = "captcha-type.not-existed";
    /**
     * 验证码不匹配
     */
    public static final String CAPTCHA_NOT_MATCH = "captcha.not-match";
}
