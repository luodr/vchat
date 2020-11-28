package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.Friend;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Mapper
@Repository
public interface FriendMapper  {


    @ResultMap("FriendUserResultMap")
    @Select("select *  from friend ,user where friend.friend_id=user.id and friend.user_id=#{userId} and friend.created_at is null")
     List<Friend> getFriends(int userId);
    @Update("update friend set deleteAt =now() where user_id=#{userId} and friend_id=#{friendId}")
    boolean softDeleteFriend(int userId,int friendId);
}
