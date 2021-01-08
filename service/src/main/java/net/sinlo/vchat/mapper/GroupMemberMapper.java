package net.sinlo.vchat.mapper;

import net.sinlo.vchat.dto.group.CreateGroupDto;
import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.entity.GroupMember;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
             " <foreach collection=\"createGroupDtos\" item=\"item\" index=\"index\" open=\"\" close=\"\" separator=\",\">  " +
             "(#{item.id},#{group_id},now(),now())"+
             "</foreach>"+
             "</script>")
     boolean joinGroup(CreateGroupDto[] createGroupDtos,int group_id);
     @Delete("delete from groupMember where user_id=#{userId} and group_id=#{groupID }")
     boolean leaveGroup(int groupID,int userId);
}
