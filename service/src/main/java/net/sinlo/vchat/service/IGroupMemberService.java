package net.sinlo.vchat.service;

import net.sinlo.vchat.entity.GroupMember;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */

public interface IGroupMemberService {
    List<GroupMember> getGroupMemberList(int userId);

    boolean leaveGroup(int groupID,int userId);
}
