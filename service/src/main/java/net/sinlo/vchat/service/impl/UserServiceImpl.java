package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.mapper.UserMapper;
import net.sinlo.vchat.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean register(User user) {
        return super.save(user);
    }
}
