package com.krb.component_data.bean;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public class ObjectBean {
    private String message;
    private String msg;
    private String state;

    public String getMsg() {
        //return BasicTool.isEmpty(msg) ? message : msg;
        if (msg == null){
            return message;
        }else {
            return msg ;
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
