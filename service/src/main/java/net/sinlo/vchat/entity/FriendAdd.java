package net.sinlo.vchat.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ldr
 * @since 2020-12-18
 */
@ApiModel(value="friendAdd", description="")
public class FriendAdd implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @ApiModelProperty(value = "发送方")
    private Integer send_user_id;
    @ApiModelProperty(value = "接收方")
    private Integer to_user_id;

    @ApiModelProperty(value = "状态")
    private String state;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;
    private  User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FriendAdd(Integer send_user_id, Integer to_user_id, String state) {
        this.send_user_id = send_user_id;
        this.to_user_id = to_user_id;
        this.state = state;
    }

    @Override
    public String toString() {
        return "FriendAdd{" +
                "id=" + id +
                ", send_user_id=" + send_user_id +
                ", to_user_id=" + to_user_id +
                ", state='" + state + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                ", user=" + user +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
