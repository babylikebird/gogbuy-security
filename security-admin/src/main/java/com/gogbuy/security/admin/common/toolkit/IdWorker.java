package com.gogbuy.security.admin.common.toolkit;

import java.util.UUID;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:14:26
 * ProjectName:gogbuy-security
 */
public class IdWorker {
    /**
     * 主机和进程的机器码
     */
    private static final Sequence worker = new Sequence();

    public static long getId() {
        return worker.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(worker.nextId());
    }

    /**
     * <p>
     * 获取去掉"-" UUID
     * </p>
     */
    public static synchronized String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
