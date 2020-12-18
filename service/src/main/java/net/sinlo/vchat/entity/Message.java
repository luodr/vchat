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
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;


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

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDate deleteAt;


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
        return "Chat{" +
        "id=" + id +
        ", send_user_id=" + send_user_id +
        ", to_user_id=" + to_user_id +
        ", type=" + type +
        ", context=" + context +
        ", createdAt=" + createdAt +
        ", updateAt=" + updateAt +
        ", deleteAt=" + deleteAt +
        "}";
    }
}
