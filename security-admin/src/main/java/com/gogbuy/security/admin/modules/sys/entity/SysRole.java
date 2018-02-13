package com.gogbuy.security.admin.modules.sys.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable{
    private static final long serialVersionUID = 7482984623940918539L;
    private String id;
    @NotEmpty(message = "权限名不能为空")
    private String roleName;
    @Size(min = 0,max = 125,message = "角色介绍字符长度不能超过125个字符")
    private String remark;

    private Date createTime;
    @NotEmpty(message = "角色编码不能为空")
    @Size(min = 5,max = 15,message = "角色编码字符长度在5-15之间")
    private String roleValue;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue == null ? null : roleValue.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}