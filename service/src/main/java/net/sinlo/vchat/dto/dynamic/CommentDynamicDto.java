package net.sinlo.vchat.dto.dynamic;

import io.swagger.annotations.ApiModelProperty;

public class CommentDynamicDto {
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "内容")
    private String context;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
