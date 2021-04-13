package net.sinlo.vchat.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

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
@ApiModel(value="Friend对象", description="")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    public Friend() {
    }

    public Friend(Integer id, Integer user_id, Integer friend_id, String remark, User myFriend, List<Message> messages, LocalDateTime createdAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.remark = remark;
        this.myFriend = myFriend;
        this.messages = messages;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    @ApiModelProperty(value = "用户ID")
    private Integer user_id;

    @ApiModelProperty(value = "朋友的用户ID")
    private Integer friend_id;

    @ApiModelProperty(value = "备注")
    private String remark;
    // 我的朋友
    private   User myFriend;
    private  List<Message> messages;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getMyFriend() {
        return myFriend;
    }

    public void setMyFriend(User myFriend) {
        this.myFriend = myFriend;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
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
