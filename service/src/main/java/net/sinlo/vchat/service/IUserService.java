package net.sinlo.vchat.service;

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
      boolean register(User user);
      String login(User user);
}
