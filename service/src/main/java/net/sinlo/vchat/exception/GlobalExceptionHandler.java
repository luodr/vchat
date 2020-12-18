package net.sinlo.vchat.exception;

import net.sinlo.vchat.dto.ResponseDate;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理（404在ErrorController中处理）
 */
@RestControllerAdvice
public class GlobalExceptionHandler     {

    @ExceptionHandler(value = Exception.class)
    public Object handleError(HttpServletRequest request, Exception e) {
        System.out.println("?出错了???"+e.getMessage());
        if (e instanceof BindException) {
            System.out.println("?BindException???");
            // 属性 检验 异常
            return   new ResponseDate(((BindException) e).getFieldError().getDefaultMessage(),0) ;
        }
        return new ResponseDate(e.getMessage(),0) ;
    }
}