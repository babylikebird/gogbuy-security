package com.gogbuy.security.admin.modules.security.intercept;

import com.gogbuy.security.admin.modules.security.core.UrlConfigAttribute;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Mr.Yangxiufeng on 2018/1/24.
 * Time:19:39
 * ProjectName:gogbuy-security
 */
public class GogFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Sets the internal request map from the supplied map. The key elements should be of
     * type {@link RequestMatcher}, which. The path stored in the key will depend on the
     * type of the supplied UrlMatcher.
     *
     * @param requestMap order-preserving map of request definitions to attribute lists
     */
    public GogFilterInvocationSecurityMetadataSource() {
    }

    /**
     * <P>返回了所有定义的权限资源</P>
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
//
//        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
//                .entrySet()) {
//            allAttributes.addAll(entry.getValue());
//        }

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
        ConfigAttribute configAttribute = new UrlConfigAttribute(request);
        allAttributes.add(configAttribute);
        return allAttributes;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


}
