package net.sinlo.vchat.dto.message;

import io.swagger.annotations.ApiModelProperty;

public class TranslateDto {
    @ApiModelProperty(value = "相对路径")
    private String text;
    @ApiModelProperty(value = "源语言")
    private String source;
    @ApiModelProperty(value = "目标语言")
    private String target;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
