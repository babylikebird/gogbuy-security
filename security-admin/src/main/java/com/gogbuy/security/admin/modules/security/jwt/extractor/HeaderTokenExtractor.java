package com.gogbuy.security.admin.modules.security.jwt.extractor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-08
 * Time: 12:23
 */
public interface HeaderTokenExtractor {
    String extract(String header);
}
