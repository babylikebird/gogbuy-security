package com.gogbuy.security.admin.modules.security.core;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Mr.Yangxiufeng on 2018/1/25.
 * Time:9:55
 * ProjectName:gogbuy-security
 */
public class UrlGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 2972616235188896707L;
    private  String httpMethod;

    private  String code;

    private  String url;

    public UrlGrantedAuthority(String httpMethod, String url,String code) {
        this.httpMethod = httpMethod;
        this.url = url;
        this.code = code;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getAuthority() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlGrantedAuthority authority = (UrlGrantedAuthority) o;

        if (httpMethod != null ? !httpMethod.equals(authority.httpMethod) : authority.httpMethod != null) return false;
        if (code != null ? !code.equals(authority.code) : authority.code != null) return false;
        return url != null ? url.equals(authority.url) : authority.url == null;
    }

    @Override
    public int hashCode() {
        int result = httpMethod != null ? httpMethod.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
