package com.gogbuy.security.admin.modules.sys.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-22
 * Time: 14:05
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo implements Serializable{

    private static final long serialVersionUID = 8533716262170300353L;
    private String id;

    private String name;

    private String code;

    private String uri;

    private String method;

    private String icon;

    private String parentId;

    private Integer type;

    private Integer orderNum;

    private String description;

    private Integer level;

    private String path;

    private Boolean checked=false;

    private String tag="menu";

    private List<MenuVo> childMenu;

    private List<ElementVo> elementVos;

    public MenuVo() {
    }

    public MenuVo(SysMenu sysMenu) {
        this.id = sysMenu.getId();
        this.name = sysMenu.getName();
        this.code = sysMenu.getCode();
        this.uri = sysMenu.getUri();
        this.method = sysMenu.getMethod();
        this.icon = sysMenu.getIcon();
        this.parentId = sysMenu.getParentId();
        this.type = sysMenu.getType();
        this.orderNum = sysMenu.getOrderNum();
        this.description = sysMenu.getDescription();
        this.level = sysMenu.getLevel();
        this.path = sysMenu.getPath();
    }

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

    public List<MenuVo> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<MenuVo> childMenu) {
        this.childMenu = childMenu;
    }

    public List<ElementVo> getElementVos() {
        return elementVos;
    }

    public void setElementVos(List<ElementVo> elementVos) {
        this.elementVos = elementVos;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
