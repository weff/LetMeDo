package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/9/13.
 * 商品详情跳转到购物车接口
 */

public class GoodsToCartsBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1505294380455
     * response : {}
     */

    private int code;
    private Object message;
    private long timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
