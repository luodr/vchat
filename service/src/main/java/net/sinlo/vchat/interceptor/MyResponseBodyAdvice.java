package net.sinlo.vchat.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sinlo.vchat.dto.ResponseDate;
import net.sinlo.vchat.dto.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {

//        //获取当前处理请求的controller的方法
//        String methodName=methodParameter.getMethod().getName();
//        System.err.println("java---"+methodName);
//        if(methodName.equals("login"))
//        return true;

        return  true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (o instanceof String) {
            ObjectMapper om = new ObjectMapper();
            try {
                return om.writeValueAsString(new ResponseDate(o,1));
            } catch (JsonProcessingException e) {
                System.err.println("返回类型转换错误");
                throw  new RuntimeException("返回类型转换错误");
            }
        }
        if (o instanceof Result) {
            return o;
        }
        ResponseDate data=new ResponseDate(o,1);

        return data;
    }
}