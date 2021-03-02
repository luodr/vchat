package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.dto.RequestChatMessage;
import net.sinlo.vchat.entity.Message;
import net.sinlo.vchat.mapper.MessageMapper;
import net.sinlo.vchat.service.IMessagetService;

import net.sinlo.vchat.util.TencentAipUtil;
import net.sinlo.vchat.util.AipSpeechUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private AipSpeechUtil speechUtil;
    @Autowired
    private TencentAipUtil tencentAipUtil;
    @Override
    public List<Message> geMeesages(int userId) {
        return this.messageMapper.geMeesages(userId);
    }
    @Value("${upload.file.path}")
    private  String uploadPath;
    @Override
    public boolean readMessage(int userId, int send_userId) {
        return this.messageMapper.readMessage(userId,send_userId);
    }

    @Override
    public Message sendMessage(int userId,RequestChatMessage chatMessage) {
        Message message=new Message(userId,chatMessage.getTarget_id(),chatMessage.getMessageType(),chatMessage.getContent());
        message.updateAt();
        System.out.println(message);
        this.messageMapper.sendMessage(message);

        message.setSelf(true);
        return message;
    }

    @Override
    public JSONObject speech(String path) {
        return speechUtil.asrWav(uploadPath+"/"+path,"wav");
    }

    public String speechTX(String path) {
        return tencentAipUtil.asrWav(uploadPath+"/"+path,"wav");
    }

    @Override
    public String translated(String text, String source, String taeget) {
        return tencentAipUtil.translated(text,source,taeget);
    }

    @Override
    public String imgToText(String path) {
        return tencentAipUtil.imgToText(uploadPath+"/"+path);
    }
}
