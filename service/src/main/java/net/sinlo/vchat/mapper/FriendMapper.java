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
    @Select("select " +
            "\tuser.*,\n" +
            "\tfriend.*,\n" +
            "\tm.type AS m_type,\n" +
            "\tm.updateAt AS m_updateAt,\n" +
            "\tm.to_user_id as m_to_user_id,\n" +
            "\tm.send_user_id AS m_send_user_id,\n" +
            "\tm.isRead AS m_isRead ,\n" +
            "\tm.id as m_id,\n" +
            "\tm.context as m_context" +
            "  from friend left JOIN user on user.id=friend.friend_id left JOIN message as m on m.to_user_id=friend.id or m.send_user_id=friend.id  where friend.user_id=#{userId} ")
     List<Friend> getFriends(int userId);
    @Update("update friend set deleteAt =now() where user_id=#{userId} and friend_id=#{friendId}")
    boolean softDeleteFriend(int userId,int friendId);
}
