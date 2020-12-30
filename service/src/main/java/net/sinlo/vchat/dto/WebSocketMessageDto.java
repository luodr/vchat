package net.sinlo.vchat.dto;

import java.io.Serializable;

public class WebSocketMessageDto<T> implements Serializable {
   private  String type;
   private  T data;

    public WebSocketMessageDto(String type, T data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
