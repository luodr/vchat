package net.sinlo.vchat.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@ApiModel(value="登录", description="")
public class LoginDto implements Serializable {
    @NotBlank(message = "phone is notNull")
    @ApiModelProperty(value = "手机号")
    private String phone;
    @NotBlank(message = "password is notNull")
    @ApiModelProperty(value = "密码")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
        " phone=" + phone +
        ", password=" + password +
        "}";
    }
}
