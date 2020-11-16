package net.sinlo.vchat.service;

import net.sinlo.vchat.dto.user.GetCodeDto;
import net.sinlo.vchat.dto.user.LoginDto;
import net.sinlo.vchat.dto.user.UserRegisterDto;
import net.sinlo.vchat.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
public interface IUserService extends IService<User> {
      boolean register(UserRegisterDto dto);
      String login(LoginDto dto);
      boolean sendCode(GetCodeDto dto);
}
