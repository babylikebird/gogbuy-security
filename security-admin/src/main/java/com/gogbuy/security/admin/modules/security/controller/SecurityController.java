package com.gogbuy.security.admin.modules.security.controller;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.HttpHelper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:49
 * ProjectName:gogbuy-security
 */
@RestController
public class SecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request) {
        if (HttpHelper.isAjaxRequest(request)) {
            return new ModelAndView("/login/ajax");
        } else {
            return new ModelAndView("login");
        }

    }

    @RequestMapping(value = "/login/success", method = RequestMethod.GET)
    public Map<String, Object> loginSuccess(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl = null;
        if (savedRequest != null) {
            targetUrl = savedRequest.getRedirectUrl();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("targetUrl", targetUrl);
        return result;
    }

    @RequestMapping(value = "/login/failure", method = RequestMethod.GET)
    public  R loginFailure(HttpServletRequest request, HttpServletResponse response) {
        AuthenticationException ae = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        R r = new R();
        r.setCode(4003);
        r.setMsg("登录失败");
        r.setDescription(ae.getMessage());
        return r;
    }

    @RequestMapping(value = "/login/ajax", method = RequestMethod.GET)
    public R loginAjax() {
        R r = new R();
        r.setCode(4003);
        r.setMsg("你需要登录");
        return r;
    }

//    @RequestMapping(value = "/security/user", method = RequestMethod.GET)
//    public Map<String, Object> securityUser(HttpServletRequest request) {
//        MyUserDetails user = UserUtil.getCurrentUser();
//        Map<String, Object> result = new HashMap<String, Object>();
//        StringBuilder userRole = new StringBuilder();
//        if (user != null) {
//            result.put("userId", user.getUserId());
//            result.put("userName", user.getUsername());
//            Collection<? extends GrantedAuthority> roleLst = user.getAuthorities();
//            for (GrantedAuthority sga : roleLst) {
//                userRole.append(sga.toString() + "; ");
//            }
//        }
//        result.put("userRole", userRole.toString());
//        result.put("message", "This message is only visible to the user");
//        return result;
//    }
//
//    @RequestMapping(value = "/security/admin", method = RequestMethod.GET)
//    public Map<String, Object> securityAdmin(HttpServletRequest request) {
//        MyUserDetails user = UserUtil.getCurrentUser();
//        Map<String, Object> result = new HashMap<String, Object>();
//        StringBuilder userRole = new StringBuilder();
//        if (user != null) {
//            result.put("userId", user.getUserId());
//            result.put("userName", user.getUsername());
//            Collection<? extends GrantedAuthority> roleLst = user.getAuthorities();
//            for (GrantedAuthority sga : roleLst) {
//                userRole.append(sga.toString() + "; ");
//            }
//        }
//        result.put("userRole", userRole.toString());
//        result.put("message", "This message is only visible to the admin");
//        return result;
//    }

    @RequestMapping(value = "/user/account", method = RequestMethod.GET)
    public Map<String, Object> getUserAcctunt(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", "需要进行完整认证的请求（不是通过Remember-me功能进行的认证）");
        return result;
    }
}
