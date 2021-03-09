package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.dto.group.CreateGroupDto;
import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.entity.GroupMember;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.mapper.GroupMapper;
import net.sinlo.vchat.mapper.GroupMemberMapper;
import net.sinlo.vchat.service.IGroupService;
import net.sinlo.vchat.service.IUserService;
import net.sinlo.vchat.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Service
public class GroupServiceImpl implements IGroupService {
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GroupMemberMapper groupMemberMapper;

    @Autowired
    IUserService userService;
    @Override
    public Group createGroup(User user, CreateGroupDto[] createGroupDtos) {
        StringBuffer name = new StringBuffer();

        for (int i = 0; i < createGroupDtos.length; i++) {
            System.out.println(createGroupDtos[i]);
            name.append(createGroupDtos[i].getName());
            if (i == 2) {
                name.append("...");
                break;
            }
            if( i < createGroupDtos.length-1){
                name.append("、");
            }

        }
        Group group = new Group(name.toString(), user.getId(), LocalDateTime.now());
        groupMapper.createGroup(group);
        groupMemberMapper.joinGroup(createGroupDtos,group.getId());

        group.setGroupMembers(new ArrayList<>());
        group=groupMapper.findByID(group.getId());
        group.setUsers(userService.findByGroupId(group.getId()));
        WebSocketServer.createRooms(createGroupDtos,group.getId(),group);
        return  group;
    }

    @Override
    public boolean joinGroup(int groupId,CreateGroupDto[] createGroupDtos) {
        return  groupMemberMapper.joinGroup(createGroupDtos,groupId);
    }

    @Override
    public List<Group> findByUserID(int userID) {
        return groupMapper.findByUserID(userID);
    }
}
