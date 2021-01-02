package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.Dynamic;
import net.sinlo.vchat.entity.Good;
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
 * @since 2021-1-1
 */
@Mapper
@Repository
public interface GoodMapper {
    @SelectKey(keyColumn = "id",keyProperty = "id",before = false,resultType =Integer.class,statement = {" select last_insert_id()"})
    @Insert(" insert into good(dynamic_id,user_id,createdAt,updateAt) values(#{dynamic_id},#{user_id},now(),now())")
      void  goodDynamic(Good good);
}
