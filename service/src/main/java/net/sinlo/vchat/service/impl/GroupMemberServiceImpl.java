package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.GroupMember;
import net.sinlo.vchat.mapper.GroupMemberMapper;
import net.sinlo.vchat.service.IGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Service
public class GroupMemberServiceImpl implements IGroupMemberService {
    @Autowired
    GroupMemberMapper groupMemberMapper;

    @Override
    public ArrayList<GroupMember> getGroupMemberList(int userId) {
        return groupMemberMapper.getGroupMemberList(userId);
    }
}
