package com.gogbuy.security.oauth2.modules.sys.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杨秀峰123
 * @since 2018-01-30
 */
@TableName("sys_privilege")
public class SysPrivilege extends Model<SysPrivilege> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 资源ID
     */
    @TableField("resource_id")
    private String resourceId;
    /**
     * 资源类型（菜单：direct，menu，页面元素：button，uri）
     */
    @TableField("resource_type")
    private String resourceType;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;


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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysPrivilege{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", resourceId=" + resourceId +
        ", resourceType=" + resourceType +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        "}";
    }
}
