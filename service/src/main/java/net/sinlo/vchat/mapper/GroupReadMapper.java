package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.GroupChat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
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
public interface GroupReadMapper {
    @Insert("insert into  group_chat_read(user_id,group_chat_id,`read`,createdAt,updateAt) values(#{user_id},#{group_chat_id},1,now(),now())")
    boolean readGroupChat(int user_id ,int group_chat_id);

 // insert into group_chat_read(user_id,group_chat_id,``,createdAt,updateAt) values(?,?,1,now(),now())
}
