package net.sinlo.vchat.dto;

import java.io.Serializable;

public class ResponseChatMessage implements Serializable {
    private int send_user_id;
    private String contentType;
    private String content;
    private String messageType;

    public ResponseChatMessage(int send_user_id, String contentType, String content, String messageType) {
        this.send_user_id = send_user_id;
        this.contentType = contentType;
        this.content = content;
        this.messageType = messageType;
    }

    public int getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(int send_user_id) {
        this.send_user_id = send_user_id;
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
