package net.sinlo.vchat.service;

import net.sinlo.vchat.entity.Friend;
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
@Mapper
public interface IFriendService  {

    List<Friend>  getFriends(int userId) ;
    boolean deleteFriend(int userId,int friendId);
}
