package net.sinlo.vchat.dto;

import io.swagger.annotations.ApiModelProperty;

public class MessageReadDto {
   private  int chatID;
    private  String type;

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageReadDto{" +
                "chatID=" + chatID +
                ", type='" + type + '\'' +
                '}';
    }
}
