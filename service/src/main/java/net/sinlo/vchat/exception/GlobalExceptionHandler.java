package net.sinlo.vchat.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理（404在ErrorController中处理）
 */
@RestControllerAdvice
public class GlobalExceptionHandler     {

    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest request, Exception e) {

        if (e instanceof BindException) {
            // 属性 检验 异常
            return ((BindException) e).getFieldError().getDefaultMessage();
        }
        return e.getMessage();
    }


}