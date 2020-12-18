package net.sinlo.vchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

    @Autowired
    private StringRedisTemplate template;

    /**
     * redis发布消息，就是往指定频道发消息
     *
     * @param channel 订阅的频道
     * @param message 发布 的内容
     */
    public void sendMessage(String channel, String message) {

        template.convertAndSend(channel, message);
        System.out.println("您已经向频道：" + channel + "发布了一条消息，内容是： " + message);
    }
}
