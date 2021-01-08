package net.sinlo.vchat.dto;

import java.io.Serializable;

public class RequestChatMessage implements Serializable {
    private int target_id;
    private String contentType;
    private String content;

    private String messageType;


    public RequestChatMessage(int target_id, String contentType, String content, String messageType) {
     this.target_id=target_id;
        this.contentType = contentType;
        this.content = content;
        this.messageType = messageType;

    }

    public int getTarget_id() {
        return target_id;
    }

    public void setTarget_id(int target_id) {
        this.target_id = target_id;
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
