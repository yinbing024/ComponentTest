package com.krb.component_data.bean;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public class BaseResponse {
    public static final String SUCCESS = "0";

    private int code ;
    private String message ;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
