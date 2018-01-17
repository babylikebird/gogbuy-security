package com.gogbuy.security.admin.common.utils;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:35
 * ProjectName:gogbuy-security
 */
public class StatusCode {

    /**
     * 成功都以200开始，针对各个业务可以设置20001,20002等
     */
    public static final int SUCCESS = 200;

    /**
     * 认证相关
     */
    public static final int NOT_FOUND = 404;
    public static final int UNAUTHORIZED = 401;
    public static final int LOGIN_FAILURE = 40101;//用户名密码错误
    public static final int LOGIN_FAILURE_CODE_ERROR = 40102;//验证码错误
    public static final int LOGIN_FAILURE_USER_NOT_EXIST=40401;//登录失败，用户不存在
    /**
     * 发生错误等，以500开头设置
     */
    public static final int ERROR = 500;
}
