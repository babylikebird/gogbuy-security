package com.gogbuy.security.oauth2.modules.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色与部门对应关系
 * </p>
 *
 * @author 杨秀峰123
 * @since 2018-01-30
 */
@TableName("sys_dept_role")
public class SysDeptRole extends Model<SysDeptRole> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private String deptId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysDeptRole{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", deptId=" + deptId +
        "}";
    }
}
