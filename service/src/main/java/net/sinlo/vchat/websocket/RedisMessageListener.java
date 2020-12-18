package net.sinlo.vchat.websocket;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;

/***
 * 定义一个RedisMessageListener类实现MessageListener接口，做消息订阅的处理
 */
@Component
public class RedisMessageListener implements MessageListener {

    //用户的session
    private Session session;

    //用户的ID
    private String userId;

    //在线人数
    private Integer onlineCount;

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String topic = new String(pattern);

        String msg = new String(message.getBody());
        if (null != session && session.isOpen()) {
            //用户在线而且订阅了主题
            try {
                synchronized (session) {
                    msg = "用户ID是：" + userId + "您好！  您正则与: " + onlineCount + "  人在线观看，" +
                            "共同订阅的话题:《" + topic + "》发布了消息，内容是：《" + msg + "》";
                    System.out.println(msg);
                    session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                System.out.println("发送消息异常");
            }
        } else if (userId != null) {
            //用户不在线但是订阅了主题
            System.out.println("用户:  " + userId + "  当前不在线，但是他已经订阅了，所以我们无法给他实时推出数据");
            doLiXian(userId);

        } else {

        }
    }

    public void doLiXian(String userId) {
        System.out.println(userId + "我们可以根据用户的ID来给用户发送一些消息，都在这个方法里完成，比如发邮件、发短信之类的");
    }
}