package com.gogbuy.security.admin.common.excetion;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:17:03
 * ProjectName:gogbuy-security
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    // 请求参数缺少或者校验不通过
    // 根据特定的异常返回指定的 HTTP 状态码
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public R handleValidationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : errors) {
            strBuilder.append(violation.getMessage() + ",");
        }
        if (strBuilder.length() > 0){
            strBuilder.delete(strBuilder.lastIndexOf(","),strBuilder.lastIndexOf(",")+1);
        }
        ex.printStackTrace();
        return R.failure(StatusCode.BAD_REQUEST,strBuilder.toString());
    }
    // 通用异常的处理，返回500
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception ex) {
        ex.printStackTrace();
        return R.failure(StatusCode.ERROR, ex.getMessage());
    }

}
