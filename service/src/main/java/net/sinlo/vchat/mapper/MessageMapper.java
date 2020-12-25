package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.Message;
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
public interface MessageMapper {
    @Select("select * from message where  send_user_id=#{userId} or to_user_id=#{userId}   Order BY  send_user_id, createdAt DESC " )
     List<Message>  geMeesages(int userId);
    @Update("update message set isRead=1 where send_user_id=#{send_userId} and to_user_id=#{userId}")
    boolean readMessage(int userId,int send_userId);
    @SelectKey(keyColumn = "id",keyProperty = "id",before = false,resultType =Integer.class,statement = {" select last_insert_id()"})
    @Insert("insert into message(send_user_id,to_user_id,type,context,createdAt,updateAt,isRead)" +
            " values(#{send_user_id},#{to_user_id},#{type},#{context},now(),now(),0)")
    void sendMessage(Message message);
}
