package net.sinlo.vchat.dto.dynamic;

import io.swagger.annotations.ApiModelProperty;

public class DynamicDto {
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "内容")
    private String context;
    @ApiModelProperty(value = "多张图片 , 分割")
    private String images;

    public DynamicDto(String type, String context, String images) {
        this.type = type;
        this.context = context;
        this.images = images;
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

    @Override
    public String toString() {
        return "DynamicDto{" +
                "type='" + type + '\'' +
                ", context='" + context + '\'' +
                ", images='" + images + '\'' +
                '}';
    }
}
