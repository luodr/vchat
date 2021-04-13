package net.sinlo.vchat.entity;


import java.time.LocalDateTime;
import java.io.Serializable;

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
@ApiModel(value = "User对象", description = "")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    public User() {
    }

    public User(Integer id, String phone, String password, String city, String signature, String name, String img, LocalDateTime createdAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.city = city;
        this.signature = signature;
        this.name = name;
        this.img = img;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    @ApiModelProperty(value = "手机号",dataType = "varchar")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "签名")
    private String signature;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String img;
    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", signature='" + signature + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
