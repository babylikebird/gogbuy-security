package com.gogbuy.security.oauth2.modules.security.endpoint;

import com.gogbuy.security.oauth2.common.model.R;
import com.gogbuy.security.oauth2.common.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-12
 * Time: 13:44
 */
@FrameworkEndpoint
public class RestLogoutEndpoint {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/logout", method= RequestMethod.POST)
    public @ResponseBody R logout(String access_token){
        if (consumerTokenServices.revokeToken(access_token)){
            return R.failure(StatusCode.FAILURE,"登出失败");
        }else {
            return R.ok("成功登出");
        }

    }
}
