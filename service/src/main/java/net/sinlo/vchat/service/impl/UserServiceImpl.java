package net.sinlo.vchat.service.impl;
        import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
        import net.sinlo.vchat.entity.User;
        import net.sinlo.vchat.mapper.UserMapper;
        import net.sinlo.vchat.service.IUserService;
        import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
        import net.sinlo.vchat.util.SMSUtil;
        import net.sinlo.vchat.util.TokenUtil;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
      @Autowired
     SMSUtil smsUtil;
    /**
     * 注册账号
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        QueryWrapper qw = new QueryWrapper();
        qw.ge("phone", user.getPhone());
        User u = super.getOne(qw);
        // 账号已被注册
        if(u!=null){
            return  false;
        }
        return super.save(user);
    }

    /**
     * 登录
     * @param user
     * @return token
     */
    @Override
    public String login(User user) {
        QueryWrapper qw = new QueryWrapper();
        qw.ge("phone", user.getPhone());
        qw.ge("password", user.getPassword());
        User u = super.getOne(qw);
          if(u!=null){
             return TokenUtil.getToken(user);
          }
        return "";
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    public  boolean sendCode(String phone){
        String code=smsUtil.sendSms(phone);

        return true;
    }
}
