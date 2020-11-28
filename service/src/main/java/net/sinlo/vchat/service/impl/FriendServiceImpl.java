package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.Friend;
import net.sinlo.vchat.mapper.FriendMapper;
import net.sinlo.vchat.service.IFriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
public class FriendServiceImpl implements IFriendService {
    @Autowired
    FriendMapper mapper;

    @Override
    public List<Friend> getFriends(int userId) {
        return mapper.getFriends(userId);
    }

    @Override
    public boolean deleteFriend(int userId, int friendId) {
        return this.mapper.softDeleteFriend(userId,friendId);
    }
}
