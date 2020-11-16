package net.sinlo.vchat.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@ApiModel(value="登录", description="")
public class GetCodeDto implements Serializable {
    @NotBlank(message = "phone is notNull")
    @ApiModelProperty(value = "手机号")
    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public String toString() {
        return "getCodeDto{" +

        ", phone=" + phone +

        "}";
    }
}
