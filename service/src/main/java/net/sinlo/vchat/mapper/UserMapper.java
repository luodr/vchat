package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Mapper
public interface UserMapper  {
    @Select("select * from user where phone like #{phone} and password like #{password}")
      User login(String phone,String password);
    @Select("select * from user where phone like #{phone}   limit 1")
    User findByPhoneOne(String phone);

    @Insert("insert  into user(phone,password,created_at) values(#{phone},#{password},now())")
    boolean insertOne(User user);
}
