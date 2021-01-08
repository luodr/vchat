package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.dto.RequestChatMessage;
import net.sinlo.vchat.dto.group.CreateGroupDto;
import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.entity.GroupChat;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.mapper.GroupChatMapper;
import net.sinlo.vchat.mapper.GroupMapper;
import net.sinlo.vchat.mapper.GroupMemberMapper;
import net.sinlo.vchat.service.IGroupChatService;
import net.sinlo.vchat.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class GroupChatServiceImpl implements IGroupChatService {
    @Autowired
    private GroupChatMapper groupChatMapper;

    @Override
    public GroupChat sendGroupChat(int userId,RequestChatMessage groupChat) {
        GroupChat chat=new GroupChat();
        chat.setContext(groupChat.getContent());
        chat.setCreatedAt(LocalDateTime.now());
        chat.setUpdateAt(LocalDateTime.now());
        chat.setSend_user_id(userId);
        chat.setTo_group_id(groupChat.getTarget_id());
        chat.setType(groupChat.getMessageType());
        System.out.println(chat);
        groupChatMapper.sendGroupChat(chat);
        return chat;
    }
}
