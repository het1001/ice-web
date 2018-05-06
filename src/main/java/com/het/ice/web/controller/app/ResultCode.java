package com.het.ice.web.controller.app;

/**
 * Created by Administrator on 2016/12/9.
 */
public class ResultCode {

    public static final String SUCCESS = "SUCCESS";

    // 用户已存在
    public static final String USER_EXIST = "USER_EXIST";

    // 用户不存在
    public static final String USER_NOT_EXIST = "USER_NOT_EXIST";

    // 用户创建失败
    public static final String USER_CREATE_ERROR = "USER_CREATE_ERROR";

    // 验证码还未失效
    public static final String AUTH_CODE_USEING = "AUTH_CODE_USEING";

    // 验证码发送太多了
    public static final String AUTH_CODE_SEND_MORE = "AUTH_CODE_SEND_MORE";

    // 已发送过验证码
    public static final String SEND_AUTHED = "SEND_AUTHED";

    // 已验证通过
    public static final String AUTHED = "AUTHED";

    // 还未验证通过
    public static final String NOT_AUTHED = "NOT_AUTHED";

    // 验证码校验错误
    public static final String AUTH_ERROR = "AUTH_ERROR";

    // 短信发送失败
    public static final String SMS_SEND_ERROR = "SMS_SEND_FAILD";
}
