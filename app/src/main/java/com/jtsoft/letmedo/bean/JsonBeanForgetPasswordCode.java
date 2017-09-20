package com.jtsoft.letmedo.bean;

/**
 * Created by Administrator on 2017/6/25.
 */

public class JsonBeanForgetPasswordCode {


    /**
     * code : 200
     * message : null
     * response : {}
     */

    private int code;
    private Object message;
    private ResponseBean response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
    }
}
