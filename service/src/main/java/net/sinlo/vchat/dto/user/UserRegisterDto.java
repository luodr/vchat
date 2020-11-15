package net.sinlo.vchat.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@ApiModel(value="User对象", description="")
public class UserRegisterDto implements Serializable {
    @NotBlank(message = "phone is notNull")
    @ApiModelProperty(value = "手机号")
    private String phone;
    @NotBlank(message = "password is notNull")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String img;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "签名")
    private String signature;

    @ApiModelProperty(value = "昵称")
    private String name;





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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "UserDto{" +

        ", phone=" + phone +
        ", password=" + password +
        ", img=" + img +
        ", city=" + city +
        ", signature=" + signature +
        ", name=" + name +
        "}";
    }
}
