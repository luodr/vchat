package net.sinlo.vchat.service;

import net.sinlo.vchat.dto.user.GetCodeDto;
import net.sinlo.vchat.dto.user.LoginDto;
import net.sinlo.vchat.dto.user.UserRegisterDto;
import net.sinlo.vchat.entity.User;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
public interface IUserService  {
      Map register(UserRegisterDto dto);
      String login(LoginDto dto);
      boolean sendCode(GetCodeDto dto);
      User findByPhone(String phone);
      boolean updateImg(int userId,String img);
      User search(String phone);
      List<User> findByGroupId(int groupId);
      boolean updateName(int userId,String name);

      Map updatePassword(UserRegisterDto dto);

}
