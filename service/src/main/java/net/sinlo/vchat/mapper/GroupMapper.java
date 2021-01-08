package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.Group;
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
public interface GroupMapper {
    @SelectKey(keyColumn = "id",keyProperty = "id",before = false,resultType =Integer.class,statement = {" select last_insert_id()"})
    @Insert("insert into `group`(name,created_user_ID,createdAt) values(#{name},#{created_user_ID},#{createdAt})")
    boolean createGroup(Group group);

    @ResultMap("group")
    @Select("SELECT\n" +
            "\tg.id AS id,\n" +
            "\tg.NAME AS NAME,\n" +
            "\tg.type AS type,\n" +
            "\tg.remark AS remark,\n" +
            "\tg.max_num AS max_num,\n" +
            "\tg.created_user_ID AS created_user_ID,\n" +
            "\tg.createdAt AS createdAt,\n" +
            "\tg.updateAt AS updateAt,\n" +
            "\tg.deleteAt AS deleteAt,\n" +
            "\tmember.user_id AS member_user_id,\n" +
            "\tmember.id AS member_id,\n" +
            "\tmember.type AS member_type,\n" +
            "\tmember.group_id AS member_group_id,\n" +
            "\tmember.createdAt AS member_createdAt,\n" +
            "\tmember.updateAt AS member_updateAt,\n" +
            "\tmember.deleteAt AS member_deleteAt ,\n" +
            "\tc.send_user_id as c_send_user_id,\n" +
            "\tc.type as c_type,\n" +
            "\tc.to_group_id as c_to_group_id,\n" +
            "\tc.context as c_context,\n" +
            "\tc.createdAt AS c_createdAt,\n" +
            "\tc.updateAt AS c_updateAt,\n" +
            "\tc.deleteAt AS c_deleteAt \n" +
            "FROM\n" +
            "\t`group` AS g\n" +
            "\tLEFT JOIN groupmember AS member ON member.group_id = g.id\n" +
            "\tLEFT JOIN group_chat AS c ON c.to_group_id = g.id\n" +
            "\tLEFT JOIN group_chat_read AS r ON r.group_chat_id = g.id \n" +
            "WHERE\n" +
            "\tmember.user_id = #{userID} " +
            "\tORDER BY \tc.createdAt")
    List<Group> findByUserID(int userID);

}
