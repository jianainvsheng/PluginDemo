package com.example.share.base.data;

public class BaseResponse <D> {

    public static final int CODE_SUCCESS = 1;

    public static final int CODE_FAIL = -1;

    private int code = CODE_FAIL;

    private String msg = "";

    private D data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
