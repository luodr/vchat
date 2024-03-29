package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.GroupMember;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.mapper.GroupMemberMapper;
import net.sinlo.vchat.service.IGroupMemberService;
import net.sinlo.vchat.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<GroupMember> getGroupMemberList(int userId) {
        return groupMemberMapper.getGroupMemberList(userId);
    }

    @Override
    public boolean leaveGroup(int groupID, int userId) {
         if(groupMemberMapper.leaveGroup(groupID,userId)){
             WebSocketServer.leaveRoomById(userId,groupID);
             return true;
         }else{
             return  false;
         }
    }

    @Override
    public ArrayList<User> findGroupUsersByGroupID(int group_id) {
        return groupMemberMapper.findGroupUsersByGroupID(group_id);
    }
}
