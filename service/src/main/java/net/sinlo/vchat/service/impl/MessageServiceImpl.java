package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.dto.RequestChatMessage;
import net.sinlo.vchat.entity.Message;
import net.sinlo.vchat.mapper.MessageMapper;
import net.sinlo.vchat.service.IMessagetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MessageServiceImpl implements IMessagetService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> geMeesages(int userId) {
        return this.messageMapper.geMeesages(userId);
    }

    @Override
    public boolean readMessage(int userId, int send_userId) {
        return this.messageMapper.readMessage(userId,send_userId);
    }

    @Override
    public Message sendMessage(int userId,RequestChatMessage chatMessage) {
        Message message=new Message(userId,chatMessage.getTo_user_id(),chatMessage.getMessageType(),chatMessage.getContent());
        message.updateAt();
        this.messageMapper.sendMessage(message);
        message.setSelf(true);
        return message;
    }


}
