package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/8/17.
 */

public class PersonalMsgBean {

    /**
     * code : 200
     * message : null
     * currentTimeMillis : 1502950478136
     * response : {"customerImg":null,"customerNickname":"小哥请留步","customerSex":null,"customerMobile":"18319628135"}
     */

    private int code;
    private Object message;
    private long currentTimeMillis;
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

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * customerImg : null
         * customerNickname : 小哥请留步
         * customerSex : null
         * customerMobile : 18319628135
         */

        private Object customerImg;
        private String customerNickname;
        private int customerSex;
        private String customerMobile;

        public Object getCustomerImg() {
            return customerImg;
        }

        public void setCustomerImg(Object customerImg) {
            this.customerImg = customerImg;
        }

        public String getCustomerNickname() {
            return customerNickname;
        }

        public void setCustomerNickname(String customerNickname) {
            this.customerNickname = customerNickname;
        }

        public int getCustomerSex() {
            return customerSex;
        }

        public void setCustomerSex(int customerSex) {
            this.customerSex = customerSex;
        }

        public String getCustomerMobile() {
            return customerMobile;
        }

        public void setCustomerMobile(String customerMobile) {
            this.customerMobile = customerMobile;
        }
    }
}
