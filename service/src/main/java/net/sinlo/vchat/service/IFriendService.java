package net.sinlo.vchat.service;

import net.sinlo.vchat.dto.friend.FriendRemarkDto;
import net.sinlo.vchat.entity.Friend;
import net.sinlo.vchat.entity.FriendAdd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */

public interface IFriendService  {

    List<Friend>  getFriends(int userId) ;
    boolean deleteFriend(int userId,int friendId);
     List<Integer> getFriendIDs(int userId);
    FriendAdd requestAddFriend(int userId, int friendId);
    boolean updateState(String state,int userId, int friendId);
    boolean addFriend(int userId,int friendId);
    List<FriendAdd> getFriendAdds(int userId);
    FriendAdd  getFriendById(int id);
    boolean  updateOneRemark(int userId,FriendRemarkDto dto);

}
