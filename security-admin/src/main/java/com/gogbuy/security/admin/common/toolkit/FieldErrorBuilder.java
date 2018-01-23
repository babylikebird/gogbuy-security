package com.gogbuy.security.admin.common.toolkit;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/23.
 * Time:14:03
 * ProjectName:gogbuy-security
 */
public class FieldErrorBuilder {

    public static String build(List<FieldError> fieldErrorList){
        if (fieldErrorList == null || fieldErrorList.size() == 0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldErrorList.size(); i++) {
            FieldError fieldError = fieldErrorList.get(i);
            String msg = fieldError.getDefaultMessage();
            sb.append(msg);
            if (i < fieldErrorList.size()-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
