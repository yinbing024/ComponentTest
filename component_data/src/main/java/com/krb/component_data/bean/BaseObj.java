package com.krb.component_data.bean;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public class BaseObj<T> {
    private String errorCode;
    private String errorMsg;
    private T data;

    public String getCode() {
        return errorCode;
    }

    public void setCode(String code) {
        this.errorCode = code;
    }

    public String getMessage() {
        return errorMsg;
    }

    public void setMessage(String message) {
        this.errorMsg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
