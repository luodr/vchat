package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.dto.user.GetCodeDto;
import net.sinlo.vchat.dto.user.LoginDto;
import net.sinlo.vchat.dto.user.UserRegisterDto;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.mapper.UserMapper;
import net.sinlo.vchat.service.IUserService;

import net.sinlo.vchat.util.MyBeanUtils;
import net.sinlo.vchat.util.RedisUtil;
import net.sinlo.vchat.util.SMSUtil;
import net.sinlo.vchat.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    SMSUtil smsUtil;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserMapper userMapper;

    /**
     * 注册账号
     *
     * @param dto 数据转输类
     * @return
     */
    @Override
    public boolean register(UserRegisterDto dto) {
       String code=(String)this.redisUtil.get(dto.getPhone()+"code");
        System.err.println(dto);
        // 校验验证码
      if(code!=null&&code.equals(dto.getCode())){

        User u = this.userMapper.findByPhoneOne(dto.getPhone());
        // 账号已被注册
        if (u != null) {
            return false;
        }
        // 数据转输类 --- 实体类
        User user = MyBeanUtils.copyProperties(dto, User.class);
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
        return this.userMapper.insertOne(user);
      }
      return  false;
    }

    /**
     * 登录
     *
     * @param dto
     * @return token
     */
    @Override
    public String login(LoginDto dto) {
        User user = this.userMapper.login(dto.getPhone(), dto.getPassword());
        if (user != null) {
            // 生成token
            return TokenUtil.getToken(user);
        }
        return null;
    }

    /**
     * 发送验证码
     *
     * @param dto
     * @return
     */
    public boolean sendCode(GetCodeDto dto) {
        String code = smsUtil.sendSms(dto.getPhone());
        // 把验证码保存到Redis--保存10分钟
        this.redisUtil.set(dto.getPhone() + "code", code, 600);
        return true;
    }

    @Override
    public User findByPhone(String phone) {
        return this.userMapper.findByPhoneOne(phone);
    }

    @Override
    public User search(String phone) {
        User user=this.userMapper.findByPhoneOne(phone);
        user.setPassword(null);
        return user;
    }

    @Override
    public List<User> findByGroupId(int groupId) {
        return userMapper.findByGroupId(groupId);
    }


}
