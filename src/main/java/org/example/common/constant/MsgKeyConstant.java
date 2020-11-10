package org.example.common.constant;

public class MsgKeyConstant {
    private MsgKeyConstant() {}
    /** 请求成功 */
    public static final String SUCCESS = "success";
    /** 操作失败 */
    public static final String FAIL = "failure";
    /** 认证失败 */
    public static final String UNAUTHORIZED = "unauthorized";
    /** 用户不存在 */
    public static final String SYSTEM_USER_NOT_EXISTED = "system.user.not-existed";
    /** 用户已被删除 */
    public static final String SYSTEM_USER_IS_DELETED = "system.user.deleted";
    /** 用户已被禁用 */
    public static final String SYSTEM_USER_IS_DISABLE = "system.user.disable";
    /** 用户名与密码不匹配 */
    public static final String SYSTEM_USERNAME_PASSWORD_NOT_MATCH = "system.user.password-not-match";
}
