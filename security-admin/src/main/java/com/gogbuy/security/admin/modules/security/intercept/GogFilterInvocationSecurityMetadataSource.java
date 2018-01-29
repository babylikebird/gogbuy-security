package com.gogbuy.security.admin.modules.security.intercept;

import com.gogbuy.security.admin.modules.security.core.UrlConfigAttribute;
import com.gogbuy.security.admin.modules.security.core.UrlGrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Mr.Yangxiufeng on 2018/1/24.
 * Time:19:39
 * ProjectName:gogbuy-security
 * <p>restfull  AntPathMatcher匹配规则</p>
 * <p>http://blog.csdn.net/qq_21251983/article/details/53034425</p>
 * <p>?:匹配1个字符<p>
 * <p>*:匹配0个或多个字符</p>
 * <p>**:匹配路径中的0个或多个目录</p>
 */
@Component
public class GogFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    protected final Log logger = LogFactory.getLog(getClass());

    private Set<UrlGrantedAuthority> authoritySet = new HashSet<>();

    public GogFilterInvocationSecurityMetadataSource() {
    }

    /**
     * <P>返回了所有定义的权限资源</P>
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        return allAttributes;
    }

    /**
     * <p>本次访问需要的权限<p/>
     * @param object
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();

        Set<ConfigAttribute> allAttributes = new HashSet<>();
        /**
         * 在这里判断URL是否是在资源池中，不在返回null，那么就不过滤权限
         */
        boolean isSource = false;
        Iterator<UrlGrantedAuthority> it = authoritySet.iterator();
        while (it.hasNext()){
            UrlGrantedAuthority authority = it.next();
            if (StringUtils.isEmpty(authority.getUrl()))
                continue;
            //如果数据库的method字段为null，则默认为所有方法都支持
            String httpMethod = org.apache.commons.lang.StringUtils.isNotBlank(authority.getHttpMethod()) ? authority.getHttpMethod()
                    : request.getMethod();
            //用Spring已经实现的AntPathRequestMatcher进行匹配，这样我们数据库中的url也就支持ant风格的配置了（例如：/xxx/user/**）
            String pattern = authority.getAuthority();
            AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(pattern, httpMethod);
            if (antPathRequestMatcher.matches(request)){
                ConfigAttribute configAttribute = new UrlConfigAttribute(request);
                allAttributes.add(configAttribute);
                isSource = true;
            }
        }
        if (isSource){
            return allAttributes;
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void setAuthoritySet(Set<UrlGrantedAuthority> authoritySet) {
        this.authoritySet = authoritySet;
    }
}
