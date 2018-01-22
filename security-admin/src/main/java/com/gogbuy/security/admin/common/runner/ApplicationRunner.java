package com.gogbuy.security.admin.common.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>应用启动后做相应的操作</p>
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:16:26
 * ProjectName:gogbuy-security
 */
@Component
public class ApplicationRunner implements CommandLineRunner {

    private Logger LOG = LoggerFactory.getLogger(ApplicationRunner.class);

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void run(String... args) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()){
            LOG.info(info.getMethodsCondition().getMethods().toString());
            LOG.info(info.getPatternsCondition().toString());
        }
    }
}
