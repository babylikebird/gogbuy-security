package com.gogbuy.security.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-07
 * Time: 11:05
 */
@RestController
public class ResourceController {
    @RequestMapping(value = "getResource",method = RequestMethod.GET)
    public String getResource(){
        return "i am pig";
    }

}
