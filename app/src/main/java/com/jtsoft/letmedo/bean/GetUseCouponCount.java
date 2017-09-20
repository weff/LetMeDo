package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/8/28.
 * 在确认订单页面，满足条件的可用优惠券数量接口
 */

public class GetUseCouponCount {

    /**
     * code : 200
     * message : null
     * timestamp : 1503906666105
     * response : {"availableCouponNum":2}
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
         * availableCouponNum : 2
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
