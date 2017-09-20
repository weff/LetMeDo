package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/8/26.
 * 获取个人优惠券数量接口
 */

public class GetCouponCount {

    /**
     * code : 200
     * message : null
     * timestamp : 1503736510081
     * response : {"availableCouponNum":4}
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
        /**
         * availableCouponNum : 4
         */

        private int availableCouponNum;

        public int getAvailableCouponNum() {
            return availableCouponNum;
        }

        public void setAvailableCouponNum(int availableCouponNum) {
            this.availableCouponNum = availableCouponNum;
        }
    }
}
