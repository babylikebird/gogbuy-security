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

    private String tag="element";

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementVo elementVo = (ElementVo) o;

        if (id != null ? !id.equals(elementVo.id) : elementVo.id != null) return false;
        if (code != null ? !code.equals(elementVo.code) : elementVo.code != null) return false;
        if (type != null ? !type.equals(elementVo.type) : elementVo.type != null) return false;
        if (name != null ? !name.equals(elementVo.name) : elementVo.name != null) return false;
        if (uri != null ? !uri.equals(elementVo.uri) : elementVo.uri != null) return false;
        if (method != null ? !method.equals(elementVo.method) : elementVo.method != null) return false;
        if (menuId != null ? !menuId.equals(elementVo.menuId) : elementVo.menuId != null) return false;
        if (description != null ? !description.equals(elementVo.description) : elementVo.description != null)
            return false;
        if (path != null ? !path.equals(elementVo.path) : elementVo.path != null) return false;
        if (checked != null ? !checked.equals(elementVo.checked) : elementVo.checked != null) return false;
        return tag != null ? tag.equals(elementVo.tag) : elementVo.tag == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (menuId != null ? menuId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (checked != null ? checked.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
