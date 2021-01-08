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
@ApiModel(value="Group对象", description="")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<GroupMember> groupMembers;

   private  List<User> users;

    private List<GroupChat> messages;

    public Group(String name, Integer created_user_ID, LocalDateTime createdAt) {
        this.name = name;
        this.created_user_ID = created_user_ID;
        this.createdAt = createdAt;
    }

    public Group( ){

    }

    public List<GroupChat> getMessages() {
        return messages;
    }

    public void setMessages(List<GroupChat> messages) {
        this.messages = messages;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }



    private Integer id;

    @ApiModelProperty(value = "群名")
    private String name;

    @ApiModelProperty(value = "群类型")
    private String type;

    @ApiModelProperty(value = "最大人数")
    private Integer max_num;

    @ApiModelProperty(value = "创建者")
    private Integer created_user_ID;

    @ApiModelProperty(value = "备注")
    private String remark;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMax_num() {
        return max_num;
    }

    public void setMax_num(Integer max_num) {
        this.max_num = max_num;
    }

    public Integer getCreated_user_ID() {
        return created_user_ID;
    }

    public void setCreated_user_ID(Integer created_user_ID) {
        this.created_user_ID = created_user_ID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Override
    public String toString() {
        return "Group{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", max_num=" + max_num +
        ", created_user_ID=" + created_user_ID +
        ", remark=" + remark +
        ", createdAt=" + createdAt +
        ", updateAt=" + updateAt +
        ", deleteAt=" + deleteAt +
        "}";
    }
}
