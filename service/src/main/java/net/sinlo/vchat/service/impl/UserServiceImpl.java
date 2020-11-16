package net.sinlo.vchat.service.impl;
        import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
        import net.sinlo.vchat.dto.user.GetCodeDto;
        import net.sinlo.vchat.dto.user.LoginDto;
        import net.sinlo.vchat.dto.user.UserRegisterDto;
        import net.sinlo.vchat.entity.User;
        import net.sinlo.vchat.mapper.UserMapper;
        import net.sinlo.vchat.service.IUserService;
        import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
        import net.sinlo.vchat.util.MyBeanUtils;
        import net.sinlo.vchat.util.RedisUtil;
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
      @Autowired
      RedisUtil redisUtil;
    /**
     * 注册账号
     * @param dto 数据转输类
     * @return
     */
    @Override
    public boolean register(UserRegisterDto dto) {
       String code=(String)this.redisUtil.get(dto.getPhone()+"code");
       // 校验验证码
      if(code!=null&&code.equals(dto.getCode())){
          QueryWrapper qw = new QueryWrapper();
          qw.eq("phone", dto.getPhone());
          User u = super.getOne(qw,true);
          // 账号已被注册
          if(u!=null){
              return  false;
          }
          // 数据转输类 --- 实体类
          User user = MyBeanUtils.copyProperties(dto, User.class);
          return super.save(user);
      }
      return  false;
    }

    /**
     * 登录
     * @param dto
     * @return token
     */
    @Override
    public String login(LoginDto dto) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("phone", dto.getPhone());
        qw.eq("password", dto.getPassword());
        User user = super.getOne(qw);
          if(user!=null){
              // 生成token
             return TokenUtil.getToken(user);
          }
        return null;
    }

    /**
     * 发送验证码
     * @param dto
     * @return
     */
    public  boolean sendCode(GetCodeDto dto){
         String code=smsUtil.sendSms(dto.getPhone());
        // 把验证码保存到Redis--保存10分钟
        this.redisUtil.set(dto.getPhone()+"code",code,600);
        return true;
    }
}
