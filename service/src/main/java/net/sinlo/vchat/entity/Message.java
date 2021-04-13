package net.sinlo.vchat.entity;


import java.time.LocalDate;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@ApiModel(value="Chat对象", description="")
public class Message  {

    public Message() {

    }

    public Message(Integer id, Integer send_user_id, Integer to_user_id, String type, String context, boolean isRead, LocalDateTime createdAt, LocalDateTime updateAt, LocalDate deleteAt, boolean self) {
        this.id = id;
        this.send_user_id = send_user_id;
        this.to_user_id = to_user_id;
        this.type = type;
        this.context = context;
        this.isRead = isRead;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
        this.self = self;
    }

    private Integer id;

    @ApiModelProperty(value = "发送方")
    private Integer send_user_id;
    @ApiModelProperty(value = "接收方")
    private Integer to_user_id;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "内容")
    private String context;
    @ApiModelProperty(value = "是否已读")
    private boolean isRead;

    public Message( Integer send_user_id, Integer to_user_id, String type, String context) {
        this.send_user_id = send_user_id;
        this.to_user_id = to_user_id;
        this.type = type;
        this.context = context;

    }

    public Message(Integer id, Integer send_user_id, Integer to_user_id, String type, String context, boolean isRead, LocalDateTime createdAt, LocalDateTime updateAt, LocalDate deleteAt, boolean self, boolean isWithdraw) {
        this.id = id;
        this.send_user_id = send_user_id;
        this.to_user_id = to_user_id;
        this.type = type;
        this.context = context;
        this.isRead = isRead;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
        this.self = self;
        this.isWithdraw = isWithdraw;
    }

    public void updateAt( ) {
        this.createdAt=LocalDateTime.now();
        this.updateAt=LocalDateTime.now();
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDate deleteAt;

    private boolean self;
    private boolean isWithdraw;

    public boolean isWithdraw() {
        return isWithdraw;
    }

    public boolean getWithdraw() {
        return isWithdraw;
    }
    public void setWithdraw(boolean withdraw) {
        isWithdraw = withdraw;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(Integer send_user_id) {
        this.send_user_id = send_user_id;
    }

    public Integer getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(Integer to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", send_user_id=" + send_user_id +
                ", to_user_id=" + to_user_id +
                ", type='" + type + '\'' +
                ", context='" + context + '\'' +
                ", isRead=" + isRead +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                ", self=" + self +
                '}';
    }
}
