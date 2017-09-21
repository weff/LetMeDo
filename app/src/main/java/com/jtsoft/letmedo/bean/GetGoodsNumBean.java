package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/9/21.
 */

public class GetGoodsNumBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1505963955326
     * response : {"availableCouponNum":2,"unpaidCount":0,"paidCount":0,"receivedCount":34}
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
         * unpaidCount : 0
         * paidCount : 0
         * receivedCount : 34
         */

        private int availableCouponNum;
        private int unpaidCount;
        private int paidCount;
        private int receivedCount;

        public int getAvailableCouponNum() {
            return availableCouponNum;
        }

        public void setAvailableCouponNum(int availableCouponNum) {
            this.availableCouponNum = availableCouponNum;
        }

        public int getUnpaidCount() {
            return unpaidCount;
        }

        public void setUnpaidCount(int unpaidCount) {
            this.unpaidCount = unpaidCount;
        }

        public int getPaidCount() {
            return paidCount;
        }

        public void setPaidCount(int paidCount) {
            this.paidCount = paidCount;
        }

        public int getReceivedCount() {
            return receivedCount;
        }

        public void setReceivedCount(int receivedCount) {
            this.receivedCount = receivedCount;
        }
    }
}
