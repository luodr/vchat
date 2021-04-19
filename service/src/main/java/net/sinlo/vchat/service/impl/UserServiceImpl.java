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
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public Map register(UserRegisterDto dto) {
       String code=(String)this.redisUtil.get(dto.getPhone()+"code");
        System.err.println(dto);
        Map m=new HashMap();
        m.put("data",false);

        // 校验验证码
      if(!StringUtils.isEmpty(code)&&code.equals(dto.getCode())){

        User u = this.userMapper.findByPhoneOne(dto.getPhone());
        // 账号已被注册
        if (u != null) {
            m.put("message","账号已被注册！");
            return m;
        }

        // 数据转输类 --- 实体类
         User user = MyBeanUtils.copyProperties(dto, User.class);

          user.setName(user.getPhone());
          // 默认头像
          user.setImg("/upload/2021-03-22_03-04-15_2_87.jpg");
          user.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
          m.put("message", this.userMapper.insertOne(user)?"注册成功！":"注册失败请重试！");
          m.put("data",true);
          return m;
      }
        m.put("message","验证码不正确！");
        return m;
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
    public boolean updateImg(int userId, String img) {
        return this.userMapper.updateImg(userId,img);
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

    @Override
    public boolean updateName(int userId, String name) {
        return this.userMapper.updateName(userId,name);
    }

    @Override
    public Map updatePassword(UserRegisterDto dto) {
        String code=(String)this.redisUtil.get(dto.getPhone()+"code");

        Map m=new HashMap();
        m.put("data",false);

        // 校验验证码
        if(!StringUtils.isEmpty(code)&&code.equals(dto.getCode())){

            User u = this.userMapper.findByPhoneOne(dto.getPhone());
            // 账号已被注册
            if (u == null) {
                m.put("message","账号不存在");
                return m;
            }
            if(StringUtils.isEmpty(dto.getPassword())){
                m.put("message","请输入新密码");
                return m;
            }
            if(this.userMapper.updatePassword(dto.getPhone(),dto.getPassword().trim())){
                m.put("message","密码修改成功");
                return m;
            }
            m.put("message","密码修改失败，请稍后重试");
            return m;
        }
        m.put("message","验证码不正确！");
        return m;
    }


}
