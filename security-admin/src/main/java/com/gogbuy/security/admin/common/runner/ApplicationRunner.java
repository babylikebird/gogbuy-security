package com.gogbuy.security.admin.common.runner;

import com.gogbuy.security.admin.common.annotation.AclMenu;
import com.gogbuy.security.admin.common.annotation.AclResc;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.security.core.UrlGrantedAuthority;
import com.gogbuy.security.admin.modules.security.intercept.GogFilterInvocationSecurityMetadataSource;
import com.gogbuy.security.admin.modules.security.jwt.JwtAuthenticationRequestMatcher;
import com.gogbuy.security.admin.modules.security.authentication.matcher.AuthenticationRequestMatcher;
import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import com.gogbuy.security.admin.modules.sys.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>应用启动后做相应的操作</p>
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:16:26
 * ProjectName:gogbuy-security
 */
@Component
@Order(200)
public class ApplicationRunner implements CommandLineRunner {

    private Logger LOG = LoggerFactory.getLogger(ApplicationRunner.class);
    @Autowired
    private SysElementService elementService;
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private GogFilterInvocationSecurityMetadataSource securityMetadataSource;
    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    AuthenticationRequestMatcher authenticationRequestMatcher;
    @Override
    public void run(String... args) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()){
            AclResc resc = map.get(info).getMethod().getAnnotation(AclResc.class);
            LOG.info(info.getPatternsCondition().toString());
            LOG.info(info.getMethodsCondition().getMethods().toString());
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            String method = null;
            if (!methods.isEmpty()){
                method = methods.iterator().next().name();
            }
            if (resc != null){
                String code = resc.code();
                int type = resc.type();
                String name = resc.name();
                String uri = resc.uri();
                String menuId = resc.menuId();
                if (elementService.findByCode(code) !=null){
                    continue;
                }
                String menuCode = resc.parentCode();
                if (!StringUtils.isEmpty(menuCode)){
                    SysMenu menu = menuService.findByCode(menuCode);
                    if (menu != null){
                        menuId = menu.getId();
                    }
                }
                SysElement element = new SysElement();
                element.setId(IdWorker.getIdStr());
                element.setCode(code);
                element.setType(type);
                if (StringUtils.isEmpty(uri)){
                    element.setUri(info.getPatternsCondition().toString());
                }else {
                    element.setUri(uri);
                }
                element.setMethod(method);
                element.setName(name);
                element.setMenuId(menuId);
                element.setCreateTime(new Date());
                element.setDescription(resc.descript());
                elementService.insertSelective(element);
            }
            AclMenu aclMenu = map.get(info).getMethod().getAnnotation(AclMenu.class);
            if (aclMenu != null){
                String uri = aclMenu.uri();
                String code = aclMenu.code();
                if (menuService.findByCode(code) != null){
                    continue;
                }
                String name = aclMenu.name();
                int type = aclMenu.type();
                String parentId = aclMenu.parentId();
                SysMenu menu = new SysMenu();
                menu.setId(IdWorker.getIdStr());
                menu.setMethod(method);
                menu.setUri(uri);
                menu.setCode(code);
                menu.setName(name);
                menu.setType(type);
                menu.setOrderNum(0);
                menu.setParentId(parentId);
                menu.setDescription(aclMenu.descript());
                menu.setCreateTime(new Date());
            }
        }
        loadResource();
    }
    private void loadResource(){
        List<SysMenu> menuList = menuService.findByEntity(null);
        List<SysElement> elementList = elementService.findByEntity(null);
        Set<UrlGrantedAuthority> au = new HashSet<>();
        if (menuList != null && menuList.size() > 0){
            for (SysMenu menu:menuList
                 ) {
                UrlGrantedAuthority u = new UrlGrantedAuthority(menu.getMethod(),menu.getUri(),menu.getCode());
                au.add(u);
            }
        }
        if (elementList != null && elementList.size() > 0){
            for (SysElement el:elementList
                 ) {
                UrlGrantedAuthority e = new UrlGrantedAuthority(el.getMethod(),el.getUri(),el.getCode());
                au.add(e);
            }
        }
        securityMetadataSource.setAuthoritySet(au);
        authenticationRequestMatcher.setAuthority(au);
    }
}
