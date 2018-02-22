package com.gogbuy.security.admin.modules.security.core;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Mr.Yangxiufeng on 2018/1/25.
 * Time:9:55
 * ProjectName:gogbuy-security
 */
public class UrlGrantedAuthority implements GrantedAuthority {
    private final String httpMethod;

    private final String code;

    private final String url;

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

    @Override
    public String getAuthority() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlGrantedAuthority that = (UrlGrantedAuthority) o;

        if (httpMethod != null ? !httpMethod.equals(that.httpMethod) : that.httpMethod != null) return false;
        return !(url != null ? !url.equals(that.url) : that.url != null);

    }

    @Override
    public int hashCode() {
        int result = httpMethod != null ? httpMethod.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
