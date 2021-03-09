package net.sinlo.vchat.service;

import net.sinlo.vchat.dto.RequestChatMessage;
import net.sinlo.vchat.dto.group.CreateGroupDto;
import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.entity.GroupChat;
import net.sinlo.vchat.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
public interface IGroupChatService {
    GroupChat sendGroupChat(int userId,RequestChatMessage chatMessage);
    List<GroupChat> findGroupChatByUserIsNotRead(int user_id,int group_chat_id);
    boolean readGroupChat(int user_id ,int group_chat_id);
}
