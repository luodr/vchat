package net.sinlo.vchat.service;

import net.sinlo.vchat.dto.RequestChatMessage;
import net.sinlo.vchat.entity.Message;
import org.json.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */

public interface IMessagetService {
    List<Message> geMeesages(int userId);
    boolean readMessage(int userId,int send_userId);
    Message sendMessage(int userId,RequestChatMessage chatMessage);

    JSONObject speech(String path);
     String speechTX(String path);
    String translated(String text,String source,String taeget);
    String imgToText(String path);


}
