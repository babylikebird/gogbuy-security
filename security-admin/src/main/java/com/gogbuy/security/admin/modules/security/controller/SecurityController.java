package com.gogbuy.security.admin.modules.security.controller;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.HttpHelper;
import com.gogbuy.security.admin.common.utils.StatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:49
 * ProjectName:gogbuy-security
 */
@RestController
public class SecurityController {

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request) {
        if (HttpHelper.isAjaxRequest(request)) {
            return new ModelAndView("/login/ajax");
        } else {
            return new ModelAndView("login");
        }
    }

    @RequestMapping(value = "/login/ajax", method = RequestMethod.GET)
    public R loginAjax() {
        R r = new R();
        r.setCode(StatusCode.UNAUTHORIZED);
        r.setMsg("你需要登录");
        return r;
    }

    @RequestMapping(value = "/user/account", method = RequestMethod.GET)
    public Map<String, Object> getUserAcctunt(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", "需要进行完整认证的请求（不是通过Remember-me功能进行的认证）");
        return result;
    }
}
