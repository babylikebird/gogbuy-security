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
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 菜单资源编码（必须唯一）
     */
    private String code;
    /**
     * 资源标识符
     */
    private String uri;
    /**
     * 请求方式GET\POST\
     */
    private String method;
    /**
     * 图标
     */
    private String icon;
    /**
     * 父级菜单
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 类型   0：目录   1：菜单 
     */
    private Integer type;
    /**
     * 排序（1,2，，3）
     */
    @TableField("order_num")
    private Integer orderNum;
    /**
     * 描述
     */
    private String description;
    /**
     * 几级菜单
     */
    private Integer level;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    /**
     * 页面地址
     */
    private String path;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        ", id=" + id +
        ", name=" + name +
        ", code=" + code +
        ", uri=" + uri +
        ", method=" + method +
        ", icon=" + icon +
        ", parentId=" + parentId +
        ", type=" + type +
        ", orderNum=" + orderNum +
        ", description=" + description +
        ", level=" + level +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", path=" + path +
        "}";
    }
}
