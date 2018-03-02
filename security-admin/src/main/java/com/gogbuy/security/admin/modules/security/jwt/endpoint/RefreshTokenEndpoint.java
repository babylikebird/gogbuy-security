package com.gogbuy.security.admin.modules.security.jwt.endpoint;

import com.gogbuy.security.admin.common.config.WebSecurityConfig;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.modules.security.config.TokenSettings;
import com.gogbuy.security.admin.modules.security.jwt.extractor.JwtTokenExtractor;
import com.gogbuy.security.admin.modules.security.jwt.extractor.TokenExtractor;
import com.gogbuy.security.admin.modules.security.jwt.token.JwtToken;
import com.gogbuy.security.admin.modules.security.jwt.token.JwtTokenFactory;
import com.gogbuy.security.admin.modules.security.jwt.token.RawAccessJwtToken;
import com.gogbuy.security.admin.modules.security.jwt.token.RefreshToken;
import com.gogbuy.security.admin.modules.security.model.UserContext;
import com.gogbuy.security.admin.modules.security.userdetails.GogUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-07
 * Time: 10:05
 */
@RestController
public class RefreshTokenEndpoint {
    @Autowired
    private TokenSettings jwtSettings;
    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private UserDetailsService userDetailsService;
    @RequestMapping(value = "refreshToken",method = RequestMethod.POST)
    public R refreshToken(HttpServletRequest request){
        TokenExtractor tokenExtractor = new JwtTokenExtractor();
        String tokenPayload = tokenExtractor.extract(request.getParameter(WebSecurityConfig.REFRESH_TOKEN));
        if (StringUtils.isEmpty(tokenPayload)){
            throw new AuthenticationServiceException("Authorization token cannot be blank!");
        }
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken,jwtSettings.getTokenSigningKey());
        String jti = refreshToken.getJti();
        String subject = refreshToken.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
        if (userDetails != null){
            GogUserDetails user = (GogUserDetails) userDetails;
            UserContext userContext = UserContext.create(user.getId(),user.getUsername(),user.getAuthorities());
            JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
            Map<String,String> map = new HashMap<>();
            map.put("access_token",accessToken.getToken());
            map.put("refresh_token",tokenPayload);
            map.put("expires_in",String.valueOf(jwtSettings.getTokenExpirationTime()*60));
            return R.ok().setData(map);
        }
        return null;
    }

}
