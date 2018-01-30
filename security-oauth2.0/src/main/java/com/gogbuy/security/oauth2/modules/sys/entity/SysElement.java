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
@TableName("sys_element")
public class SysElement extends Model<SysElement> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 资源编码（必须唯一）
     */
    private String code;
    /**
     * 类型 2：按钮，3：uri
     */
    private Integer type;
    /**
     * 按钮或资源名称
     */
    private String name;
    /**
     * 资源标识符
     */
    private String uri;
    /**
     * 请求方式（GET,POST,DELETE,PUT）
     */
    private String method;
    /**
     * 在哪个菜单下
     */
    @TableField("menu_id")
    private String menuId;
    /**
     * 描述
     */
    private String description;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    /**
     * 前端页面地址
     */
    private String path;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "SysElement{" +
        ", id=" + id +
        ", code=" + code +
        ", type=" + type +
        ", name=" + name +
        ", uri=" + uri +
        ", method=" + method +
        ", menuId=" + menuId +
        ", description=" + description +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", path=" + path +
        "}";
    }
}
