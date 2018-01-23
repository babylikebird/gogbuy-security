package com.gogbuy.security.admin.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:32
 * ProjectName:gogbuy-security
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable{
    private static final long serialVersionUID = -500083595227338621L;
    private static final String CODE = "code";
    private static final String MSG = "msg";

    private Integer code=200;
    private String msg="操作成功";
    private String description;
    private T data;

    private HashMap<String,Object> exend;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public R setData(T data) {
        this.data = data;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public HashMap<String, Object> getExend() {
        return exend;
    }

    public void setExend(HashMap<String, Object> exend) {
        this.exend = exend;
    }

    public R() {
        exend = new HashMap<>();
    }

    public static R failure(int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.exend.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        exend.put(key, value);
        return this;
    }
}
