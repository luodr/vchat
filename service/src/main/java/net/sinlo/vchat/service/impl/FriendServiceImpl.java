package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.Friend;
import net.sinlo.vchat.mapper.FriendMapper;
import net.sinlo.vchat.service.IFriendService;
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
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {

}
