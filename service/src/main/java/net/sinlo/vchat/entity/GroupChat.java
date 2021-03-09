package net.sinlo.vchat.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel(value = "群聊天信息", description = "")

public class GroupChat implements Serializable {

    private  int id;
    private  int send_user_id;
    private  int to_group_id;
    private  String type;
    private  String context;
    private GroupChatRead groupChatRead;

    public GroupChatRead getGroupChatRead() {
        return groupChatRead;
    }
    private  boolean read;

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
    public boolean getRead() {
        return read;
    }
    public void setGroupChatRead(GroupChatRead groupChatRead) {
        this.groupChatRead = groupChatRead;
    }

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(int send_user_id) {
        this.send_user_id = send_user_id;
    }

    public int getTo_group_id() {
        return to_group_id;
    }

    public void setTo_group_id(int to_group_id) {
        this.to_group_id = to_group_id;
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

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public String toString() {
        return "GroupChat{" +
                "id=" + id +
                ", send_user_id=" + send_user_id +
                ", to_group_id=" + to_group_id +
                ", type='" + type + '\'' +
                ", context='" + context + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                '}';
    }
}
