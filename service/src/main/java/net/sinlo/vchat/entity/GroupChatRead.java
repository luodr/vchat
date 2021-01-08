package net.sinlo.vchat.entity;


import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@ApiModel(value = "群聊天信息是否已读", description = "")

public class GroupChatRead implements Serializable {

    private  int id;
    private  int user_id;
    private  int to_group_id;
    private  boolean read;




    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;

    @Override
    public String toString() {
        return "GroupChatRead{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", to_group_id=" + to_group_id +
                ", read=" + read +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTo_group_id() {
        return to_group_id;
    }

    public void setTo_group_id(int to_group_id) {
        this.to_group_id = to_group_id;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
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

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }
}
