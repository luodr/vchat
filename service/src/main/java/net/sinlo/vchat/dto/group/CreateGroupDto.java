package net.sinlo.vchat.dto.group;

import io.swagger.annotations.ApiModelProperty;

public class CreateGroupDto {
    @ApiModelProperty(value = "被邀请人用户名")
    private String name;
    @ApiModelProperty(value = "被邀请人用户id")
    private int id;

    @Override
    public String toString() {
        return "CreateGroupDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
