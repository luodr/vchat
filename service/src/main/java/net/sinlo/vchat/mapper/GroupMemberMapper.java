package net.sinlo.vchat.mapper;

import net.sinlo.vchat.dto.group.CreateGroupDto;
import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.entity.GroupMember;

import net.sinlo.vchat.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public interface GroupMemberMapper {
     @Select("select * from groupMember where user_id=#{userId}")
     List<GroupMember> getGroupMemberList(int userId);
     @Insert("<script> " +
             "insert into groupMember(user_id,group_id,createdAt,updateAt) values " +
             " <foreach collection=\"list\" item=\"item\" index=\"index\" open=\"\" close=\"\" separator=\",\">  " +
             "(#{item.id},#{group_id},now(),now())"+
             "</foreach>"+
             "</script>")
     boolean joinGroup(List<CreateGroupDto> list,int group_id);

     @Update("update  groupMember set deleteAt=now()  where user_id=#{userId} and group_id=#{groupID }")
     boolean leaveGroup(int groupID,int userId);
     @Update("update  groupMember set deleteAt=null  where user_id=#{userId} and group_id=#{groupID }")
     boolean rejoinGroup(int groupID,int userId);
     @Select("select user.* from groupMember left join user user.id=groupMember.user_id where groupMember.group_id=#{group_id}")
     ArrayList<User> findGroupUsersByGroupID(int group_id);


     @Update("<script> " +
             "update  groupMember  set deleteAt=null where id in" +
             " <foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
             "#{item.id}"+
             "</foreach>"+
             "</script>")
     boolean updateDeleteNull(List<CreateGroupDto> list,int group_id);
}
