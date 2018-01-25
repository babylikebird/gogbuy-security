package com.gogbuy.security.admin.common.annotation;

import java.lang.annotation.*;

/**
 * Created by Mr.Yangxiufeng on 2018/1/25.
 * Time:16:48
 * ProjectName:gogbuy-security
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AclMenu {
    /**
     * <p>唯一编码</p>
     * @return
     */
    String code();

    /**
     * <p>资源名称</p>
     * @return
     */
    String name();

    /**
     * <p>资源地址</p>
     * @return
     */
    String uri();
    /**
     * <p>0：目录</p>
     * <p>1：菜单</p>
     * @return
     */
    int type() default 0;

    String parentId() default "";

    String descript() default "";
}
