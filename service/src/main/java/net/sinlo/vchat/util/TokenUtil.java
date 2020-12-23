package net.sinlo.vchat.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TokenUtil {

      static   IUserService userService;

    public static String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getPhone()+"")
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
    public  static  User getUser(String token){
        String phone;
        try {
            phone = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
      return  userService.findByPhone(phone);
    }
    @Autowired
    private   void setUserService(IUserService userService) {
        TokenUtil.userService = userService;
    }

}
