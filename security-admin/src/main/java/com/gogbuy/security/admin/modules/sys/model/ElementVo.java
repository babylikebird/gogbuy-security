package com.gogbuy.security.admin.modules.sys.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gogbuy.security.admin.modules.sys.entity.SysElement;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-22
 * Time: 14:05
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementVo implements Serializable{
    private static final long serialVersionUID = -9120932864119416677L;
    private String id;
    private String code;
    private Integer type;
    private String name;
    private String uri;

    private String method;

    private String menuId;

    private String description;

    private String path;

    private Boolean checked = false;

    public ElementVo() {
    }

    public ElementVo(SysElement element) {
        this.id = element.getId();
        this.code = element.getCode();
        this.type = element.getType();
        this.name = element.getName();
        this.uri = element.getUri();
        this.method = element.getMethod();
        this.menuId = element.getMenuId();
        this.description = element.getDescription();
        this.path = element.getPath();
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
