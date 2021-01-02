package net.sinlo.vchat.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <p>
 * 
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@ApiModel(value="Chat对象", description="")
public class Dynamic implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    @ApiModelProperty(value = "发送方")
    private Integer send_user_id;

    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "内容")
    private String context;
    @ApiModelProperty(value = "多张图片 , 分割")
    private String images;

    public boolean isOpenInput() {
        return openInput;
    }
    //为了方便前端使用
    private  boolean openInput=false;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String img;
    @ApiModelProperty(value = "备注")
    private String remark;
    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;
    private ArrayList<Good> goods;
    private ArrayList<Comment> comments;

    public void setOpenInput(boolean openInput) {
        this.openInput = openInput;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public Dynamic(String type, String context, String images) {
        this.type = type;
        this.context = context;
        this.images = images;
    }

    public Dynamic() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Dynamic(Integer send_user_id, String type, String context, String images) {
        this.send_user_id = send_user_id;
        this.type = type;
        this.context = context;
        this.images = images;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Dynamic{" +
                "id=" + id +
                ", send_user_id=" + send_user_id +
                ", type='" + type + '\'' +
                ", context='" + context + '\'' +
                ", images='" + images + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", remark='" + remark + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                '}';
    }
}
