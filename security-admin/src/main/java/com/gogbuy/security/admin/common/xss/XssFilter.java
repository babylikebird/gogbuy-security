package com.gogbuy.security.admin.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:01
 * ProjectName:gogbuy-security
 */
public class XssFilter implements Filter {
    FilterConfig filterConfig = null;

    private List<String> urlExclusion = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletPath = httpServletRequest.getServletPath();

        if (urlExclusion != null && urlExclusion.contains(servletPath)) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
        }
    }

    public List<String> getUrlExclusion() {
        return urlExclusion;
    }

    public void setUrlExclusion(List<String> urlExclusion) {
        this.urlExclusion = urlExclusion;
    }
}
