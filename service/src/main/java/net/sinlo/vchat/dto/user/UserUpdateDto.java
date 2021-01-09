package net.sinlo.vchat.dto.user;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@ApiModel(value="User对象", description="")
public class UserUpdateDto implements Serializable {


    @ApiModelProperty(value = "头像")
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
