package com.het.ice.web.controller.app;

/**
 * Created by Administrator on 2016/12/9.
 */
public class ResultCode {

    public static final String SUCCESS = "SUCCESS";

    // 用户已存在
    public static final String USER_EXIST = "USER_EXIST";

    // 用户创建失败
    public static final String USER_CREATE_ERROR = "USER_CREATE_ERROR";

    // 验证码还未失效
    public static final String AUTH_CODE_USEING = "AUTH_CODE_USEING";

    // 已验证通过（会员验证通过之后关闭app，又重新验证）
    public static final String AUTHED = "AUTHED";

    // 验证码校验错误
    public static final String AUTH_ERROR = "AUTH_ERROR";
}
