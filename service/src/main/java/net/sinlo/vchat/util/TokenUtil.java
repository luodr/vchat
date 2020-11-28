package net.sinlo.vchat.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import net.sinlo.vchat.entity.User;

public class TokenUtil {
    public static String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getPhone()+"")
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
