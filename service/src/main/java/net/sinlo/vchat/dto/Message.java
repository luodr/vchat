package net.sinlo.vchat.dto;

import java.io.Serializable;

public class Message implements Serializable  {
    private String  sendUser;//消息发送者
    private String  toUser;//用户id
    private String  type;//消息类型
    private String  content;//消息内容

    public Message() {
    }
    public Message(String sendUser, String toUser, String type, String content) {
        this.sendUser = sendUser;
        this.toUser = toUser;
        this.type = type;
        this.content = content;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
}
