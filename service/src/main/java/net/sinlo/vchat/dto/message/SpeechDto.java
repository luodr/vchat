package net.sinlo.vchat.dto.message;

import io.swagger.annotations.ApiModelProperty;

public class SpeechDto {
    @ApiModelProperty(value = "相对路径")
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
