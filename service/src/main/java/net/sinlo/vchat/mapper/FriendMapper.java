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
            "\t user.*,\n" +
            "\t friend.*,\n" +
            "\t m.type AS m_type,\n" +
            "\t m.updateAt AS m_updateAt,\n" +
            "\t m.to_user_id as m_to_user_id,\n" +
            "\t m.send_user_id AS m_send_user_id,\n" +
            "\t m.isRead AS m_isRead ,\n" +
            "\t m.id as m_id,\n" +
            "\t m.context as m_context," +
            "\t  m.send_user_id= 2 as m_self "+
            " from friend left JOIN user on user.id=friend.friend_id   " +
            " left JOIN message as m on" +
            " (m.to_user_id=#{userId} or m.send_user_id=#{userId}) " +
            "and " +
            "(m.to_user_id=user.id or m.send_user_id=user.id) " +
            " where" +
            " friend.user_id=#{userId}" +
            " ")
     List<Friend> getFriends(int userId);
    @Update("update friend set deleteAt =now() where user_id=#{userId} and friend_id=#{friendId}")
    boolean softDeleteFriend(int userId,int friendId);
    @Insert("insert into friend(user_id,friend_id,createdAt,updateAt) values(#{userId},#{friendId},now(),now())")
    boolean addFriend(int userId,int friendId);

    @Select("select friend.friend_id from friend where friend.user_id=#{userId}")
    List<Integer> getFriendIDs(int userId);

    @Update("update friend set remark =#{remark} where user_id=#{userId} and friend_id=#{friendId}")
    boolean updateOneRemark(int userId,int friendId,String remark);
}
