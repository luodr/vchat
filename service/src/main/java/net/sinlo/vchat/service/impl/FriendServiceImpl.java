package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.Friend;
import net.sinlo.vchat.entity.FriendAdd;
import net.sinlo.vchat.mapper.FriendAddMapper;
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
    @Autowired
    FriendAddMapper friendAddMapper;

    @Override
    public List<Friend> getFriends(int userId) {
        return mapper.getFriends(userId);
    }

    @Override
    public boolean deleteFriend(int userId, int friendId) {
        return this.mapper.softDeleteFriend(userId, friendId);
    }

    @Override
    public FriendAdd requestAddFriend(int userId, int friendId) {
        FriendAdd friendAdd = new FriendAdd(userId, friendId, "send");
        friendAddMapper.addFriend(friendAdd);
        return friendAdd;
    }

    @Override
    public boolean updateState(String state, int userId, int friendId) {
        FriendAdd friendAdd = new FriendAdd(friendId, userId, state);
        return friendAddMapper.updateState(friendAdd);
    }

    @Override
    public boolean addFriend(int userId, int friendId) {
        this.mapper.addFriend(userId, friendId);
        this.mapper.addFriend(friendId, userId);
        return this.updateState("agree", userId, friendId);
    }

    @Override
    public List<FriendAdd> getFriendAdds(int userId) {
        return friendAddMapper.getFriendAdds(userId);
    }

    @Override
    public FriendAdd getFriendById(int id) {
        return this.friendAddMapper.getFriendById(id);
    }

    @Override
    public List<Integer> getFriendIDs(int userId) {
        return this.mapper.getFriendIDs(userId);
    }
}
