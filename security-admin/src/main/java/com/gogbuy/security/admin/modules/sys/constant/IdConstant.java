package com.gogbuy.security.admin.modules.sys.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/23.
 * Time:9:49
 * ProjectName:gogbuy-security
 */
public class IdConstant {
    public static final String SYS_ADMIN_ID="953521191379034112";//系统管理员ID

    public static final String SYS_ROLE_ID = "954227154406969344";//系统管理员角色ID

    public static List<String> sysMenuId = new ArrayList<>();//系统菜单

    static {
        sysMenuId.add("955993501461671936");//系统设置
        sysMenuId.add("955996044740878336");//用户管理
        sysMenuId.add("955996046590566400");//角色管理
        sysMenuId.add("955996046800281600");//菜单管理
        sysMenuId.add("955996047009996800");//部门管理
    }

}
