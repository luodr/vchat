package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.entity.GroupChat;
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
public interface GroupChatMapper {
    @SelectKey(keyColumn = "id",keyProperty = "id",before = false,resultType =Integer.class,statement = {" select last_insert_id()"})
    @Insert("insert into `group_chat`(send_user_id,to_group_id,type,context,createdAt,updateAt) values(#{send_user_id},#{to_group_id},#{type},#{context},#{createdAt},#{updateAt})")
    boolean sendGroupChat(GroupChat groupChat);

}
