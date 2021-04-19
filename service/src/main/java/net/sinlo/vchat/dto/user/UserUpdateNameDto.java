package net.sinlo.vchat.dto.user;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel(value="User对象", description="")
public class UserUpdateNameDto implements Serializable {


    @ApiModelProperty(value = "头像")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
