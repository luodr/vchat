package net.sinlo.vchat.util;

import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class ParamUserHandlerMethodArgReslover implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(ParamUser.class) != null &&methodParameter.getParameterType() == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
      //取得HttpServletRequest
        HttpServletRequest request= (HttpServletRequest) nativeWebRequest.getNativeRequest();
        //取出request中的User
        return (User)request.getAttribute("user");
    }
}
