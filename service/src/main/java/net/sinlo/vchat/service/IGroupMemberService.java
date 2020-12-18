package net.sinlo.vchat.service;

import net.sinlo.vchat.entity.GroupMember;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */

public interface IGroupMemberService {
    ArrayList<GroupMember> getGroupMemberList(int userId);
}
