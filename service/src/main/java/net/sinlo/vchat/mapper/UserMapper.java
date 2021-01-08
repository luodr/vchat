package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
public interface UserMapper  {
    @Select("select * from user where phone like #{phone} and password like #{password}")
      User login(String phone,String password);
    @Select("select * from user where phone like #{phone}   limit 1")
    User findByPhoneOne(String phone);

    @Insert("insert  into user(phone,password,created_at) values(#{phone},#{password},now())")
    boolean insertOne(User user);

    @Select("SELECT USER\n" +
            "\t.id AS id,\n" +
            "\tUSER.phone AS phone,\n" +
            "\tUSER.NAME AS NAME,\n" +
            "\tUSER.img AS img,\n" +
            "\tUSER.signature AS signature\n" +
            "FROM\n" +
            "\tUSER LEFT JOIN groupmember ON groupmember.group_id = #{groupId} \n" +
            "WHERE\n" +
            "\tUSER.id = groupmember.user_id")
    List<User> findByGroupId(int groupId);
}
