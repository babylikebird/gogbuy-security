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
     * <p>2:按钮</p>
     * <p>3：请求资源</p>
     * @return
     */
    int type() default 2;

    String parentId() default  "";

    String parentCode() default "";

    String menuId() default "";

    String descript() default "";
}
