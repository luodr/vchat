package net.sinlo.vchat.dto;

import java.io.Serializable;

public class RequestChatMessage implements Serializable {
    private int to_user_id;
    private String contentType;
    private String content;

    private String messageType;


    public RequestChatMessage(int to_user_id, String contentType, String content, String messageType) {
        this.to_user_id = to_user_id;
        this.contentType = contentType;
        this.content = content;
        this.messageType = messageType;

    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
