package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/8/8.
 */
public class ModifyPasswordBean {

    /**
     * message : not_login
     * code : 500
     */

    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
