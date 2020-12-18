package net.sinlo.vchat.mapper;

import net.sinlo.vchat.entity.GroupMember;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;

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
public interface GroupMemberMapper {
   @Select("select * from groupMember where user_id=#{userId}")
    ArrayList<GroupMember> getGroupMemberList(int userId);
}
