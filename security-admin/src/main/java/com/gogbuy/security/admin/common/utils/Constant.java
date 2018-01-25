package com.gogbuy.security.admin.common.utils;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:12:44
 * ProjectName:gogbuy-security
 */
public class Constant {

    /***
     * <p>restfull  AntPathMatcher匹配规则</p>
     * <p>http://blog.csdn.net/qq_21251983/article/details/53034425</p>
     * <p>?:匹配1个字符<p>
     * <p>*:匹配0个或多个字符</p>
     * <p>**:匹配路径中的0个或多个目录</p>
     */

    public static final String CONTEXTPATH="/security";

    public final static String RESOURCE_REQUEST_METHOD_GET = "GET";
    public final static String RESOURCE_REQUEST_METHOD_PUT = "PUT";
    public final static String RESOURCE_REQUEST_METHOD_DELETE = "DELETE";
    public final static String RESOURCE_REQUEST_METHOD_POST = "POST";
    /**
     * 权限资源类型
     */
    public final static String RESOURCE_TYPE_DIRECT = "direct";//目录
    public final static String RESOURCE_TYPE_MENU = "menu";//菜单
    public final static String RESOURCE_TYPE_BTN = "button";//按钮
    public final static String RESOURCE_TYPE_URI = "uri";//请求地址
}
