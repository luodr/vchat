package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.Dynamic;
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
 * @since 2021-1-1
 */
@Mapper
@Repository
public interface DynamicMapper {
    @ResultMap("goodResultMap")
    @Select("<script> select dynamic.\t* ,\n" +
            "user.name as name,\n" +
            "user.img as img,\n" +
            " good.user_id as g_user_id, good.id as g_id, good.updateAt as g_updateAt, "+
            " comment.user_id as c_user_id, comment.id as c_id, comment.updateAt as c_updateAt, comment.context as c_context, "+
            "friend.remark as  remark from  dynamic LEFT JOIN USER ON USER.id = dynamic.send_user_id\n" +
            "\tLEFT JOIN friend ON friend.friend_id =dynamic.send_user_id \n" +
            "\tAND friend.user_id =#{userID}" +
            " left join good on  good.dynamic_id=dynamic.id"+
            " left join comment on  comment.dynamic_id=dynamic.id"+
            " where  dynamic.send_user_id in" +
            "<if test=\"list != null\" >" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\">" +
            "#{item}"+
            "</foreach>"+
            " </if>"+
            "Order BY dynamic.createdAt DESC  </script>" )
     List<Dynamic>  getDynamics( List list,int userID);
    @SelectKey(keyColumn = "id",keyProperty = "id",before = false,resultType =Integer.class,statement = {" select last_insert_id()"})
    @Insert(" insert into dynamic(send_user_id,type,context,createdAt,updateAt,images) values(#{send_user_id},#{type},#{context},now(),now(),#{images})")
      void  sendDynamic(Dynamic dynamic);
}
