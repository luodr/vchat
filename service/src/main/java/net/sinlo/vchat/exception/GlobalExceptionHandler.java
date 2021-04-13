package net.sinlo.vchat.exception;


import lombok.extern.slf4j.Slf4j;
import net.sinlo.vchat.dto.ResponseDate;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理（404在ErrorController中处理）
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler     {
      private  String exceptionToast="服务器繁忙请稍后再试!";
    @ExceptionHandler(value = Exception.class)
    public Object handleError(HttpServletRequest request, Exception e) {

        if (e instanceof BindException) {
            System.out.println("?出错了???"+((BindException) e).getFieldError().getDefaultMessage());
              log.warn("BindException {}",((BindException) e).getFieldError().getDefaultMessage());
            // 属性 检验 异常
            return   new ResponseDate(exceptionToast,0) ;
        }
    
        log.error("Exception {}",e.getMessage());
        return new ResponseDate(exceptionToast,0) ;
    }
}