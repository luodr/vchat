package net.sinlo.vchat.dto;

public class ResponseDate implements Result {
    private Object data;
    private Integer code;

    public ResponseDate(Object data, Integer code) {
        this.data = data;
        this.code = code;
}
    public ResponseDate() {

    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseDate{" +
                "data=" + data +
                ", code=" + code +
                '}';
    }
}
