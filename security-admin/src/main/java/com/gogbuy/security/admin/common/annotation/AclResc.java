package com.gogbuy.security.admin.common.annotation;

import java.lang.annotation.*;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:16:49
 * ProjectName:gogbuy-security
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AclResc {
    /**
     * <p>唯一编码</p>
     * @return
     */
    String code();

    /**
     *
     * @return
     */
    String name();

    /**
     * <p>资源地址</p>
     * @return
     */
    String uri();
    /**
     * <p>direct：目录</p>
     * <p>menu：菜单</p>
     * <p>button:按钮</p>
     * <p>uri：请求资源</p>
     * @return
     */
    String type();
    boolean isMenu() default true;
}
