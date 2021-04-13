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
    public Group createGroup(User user, List<CreateGroupDto> array){
        StringBuffer name = new StringBuffer();
        array.add(new CreateGroupDto(user.getName(),user.getId()));
        System.out.println(array);

        for (int i = 0; i < array.size(); i++) {
//            System.out.println(createGroupDtos[i]);
            CreateGroupDto dto=array.get(i);
            name.append(dto.getName());
            if (i == 2) {
                name.append("...");
                break;
            }
            if( i < array.size()-1){
                name.append("、");
            }

        }
        Group group = new Group(name.toString(), user.getId(), LocalDateTime.now());
        groupMapper.createGroup(group);
        groupMemberMapper.joinGroup(array,group.getId());

        group.setGroupMembers(new ArrayList<>());
        group=groupMapper.findByID(group.getId());
        group.setUsers(userService.findByGroupId(group.getId()));
        WebSocketServer.createRooms(array,group.getId(),group);
        return  group;
    }

    @Override
    public boolean joinGroup(int groupId,List arrayList ) {
        return  groupMemberMapper.joinGroup(arrayList,groupId);
    }

    @Override
    public List<Group> findByUserID(int userID) {
        return groupMapper.findByUserID(userID);
    }
}
