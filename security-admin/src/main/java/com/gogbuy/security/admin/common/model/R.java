package com.gogbuy.security.admin.common.model;

import java.io.Serializable;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:32
 * ProjectName:gogbuy-security
 */
public class R<T> implements Serializable{
    private static final long serialVersionUID = -500083595227338621L;
    private Integer code;
    private String msg;
    private String description;
    private T data;

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

    public void setData(T data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
