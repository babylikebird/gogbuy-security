package com.gogbuy.security.admin.modules.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:9:12
 * ProjectName:gogbuy-security
 */
public class GogUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

    protected final Log logger = LogFactory.getLog(getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    public GogUrlAuthenticationFailureHandler() {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise
     * returns a 401 error code.
     * <p>
     * If redirecting or forwarding, {@code saveException} will be called to cache the
     * exception for use in the target view.
     */
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
            logger.debug("No failure URL set, sending 401 Unauthorized error");

//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
//                    "Authentication Failed: " + exception.getMessage());
        R r = R.failure(StatusCode.UNAUTHORIZED,"用户名或密码错误");
        r.setDescription(exception.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String msg = objectMapper.writeValueAsString(r);
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Authentication Failed: " + exception.getMessage());
        }finally {
            if (writer != null){
                writer.flush();
                writer.close();
            }
        }
    }

}
