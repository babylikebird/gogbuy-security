package com.gogbuy.security.admin.common.excetion;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import org.slf4j.Logger;
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

    // 异常处理方法：
    // 根据特定的异常返回指定的 HTTP 状态码
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public R handleValidationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : errors) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        ex.printStackTrace();
        return R.failure(StatusCode.FORBIDDEN,strBuilder.toString());
    }
//    @ResponseStatus(value=HttpStatus.FORBIDDEN)  // 403
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public R forbidden(Exception ex) {
//        return R.failure(StatusCode.FORBIDDEN, ex.getMessage());
//    }
    // 通用异常的处理，返回500
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception ex) {
        ex.printStackTrace();
        return R.failure(StatusCode.ERROR, ex.getMessage());
    }

}
