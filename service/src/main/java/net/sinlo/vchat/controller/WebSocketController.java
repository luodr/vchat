package net.sinlo.vchat.controller;

import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.dto.RequestChatMessage;
import net.sinlo.vchat.entity.GroupChat;
import net.sinlo.vchat.entity.Message;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IGroupChatService;
import net.sinlo.vchat.service.IMessagetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import net.sinlo.vchat.websocket.WebSocketServer;
import springfox.documentation.annotations.ApiIgnore;

@RestController()
@RequestMapping("/api/chat")
@UserLoginToken
public class WebSocketController {
    @Autowired
    IMessagetService service;
    @Autowired
    IGroupChatService groupChatService;
    /**
     * 发送私聊信息
     *
     * @param user
     * @param chatMessage
     * @return
     */
    @PostMapping("sendPrivateChat")
    public Message sendPrivateChat(@ParamUser @ApiIgnore User user,@RequestBody RequestChatMessage chatMessage) {
        Message message = service.sendMessage(user.getId(), chatMessage);
        if (message != null) {
              WebSocketServer.sendPrivate(message);
        }
        message.setSelf(true);
        return message;
    }
    /**
     * 发送私聊信息
     *
     * @param user
     * @param chatMessage
     * @return
     */
    @PostMapping("sendGroupChat")
    public GroupChat sendGroupChat(@ParamUser @ApiIgnore User user, @RequestBody RequestChatMessage chatMessage) {
        GroupChat chat = groupChatService.sendGroupChat(user.getId(),chatMessage);
        System.out.println("保存到数据库！");
        if (chat != null) {
            WebSocketServer.sendRoom(chat,user.getId());
        }
        return chat;
    }
}
