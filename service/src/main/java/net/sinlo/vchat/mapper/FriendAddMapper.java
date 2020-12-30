package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.Friend;
import net.sinlo.vchat.entity.FriendAdd;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Mapper
@Repository
public interface FriendAddMapper {

    @SelectKey(keyColumn = "id",keyProperty = "id",before = false,resultType =Integer.class,statement = {"select last_insert_id()"})
    @Insert("insert into friendAdd(send_user_id,to_user_id,state,createdAt,updateAt) values(#{send_user_id},#{to_user_id},#{state},now(),now())")
    boolean addFriend(FriendAdd friendAdd);

    @Update("update friendAdd set state=#{state} , updateAt=now() where send_user_id= #{send_user_id} and to_user_id=#{to_user_id}")
    boolean updateState(FriendAdd friendAdd);
    @ResultMap("FriendUserAddResultMap")
    @Select("select * from friendAdd left join user on user.id=friendAdd.to_user_id where friendAdd.to_user_id=#{userId}")
    List<FriendAdd> getFriendAdds(int userId);
    @ResultMap("FriendUserAddResultMap")
    @Select("select * from friendAdd left join user on user.id=friendAdd.to_user_id where friendAdd.id=#{id}")
    FriendAdd getFriendById(int id);
}
