package net.sinlo.vchat.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import net.sinlo.vchat.authorization.PassToken;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IUserService;
import net.sinlo.vchat.util.ParameterServletRequestWrapper;
import org.apache.http.impl.client.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String phone;
                try {
                    phone = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user = userService.findByPhone(phone);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                    // 将用户存到request
                    request.setAttribute("user",user);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }
}
