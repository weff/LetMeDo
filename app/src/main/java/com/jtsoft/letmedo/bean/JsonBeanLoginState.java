package com.jtsoft.letmedo.bean;

/**
 * Created by Administrator on 2017/6/25.
 * 登录接口
 */

public class JsonBeanLoginState {

    /**
     * code : 200
     * message : null
     * response : {"customerId":56286,"mobile":"17372288373","name":"章鱼","hasPassword":1,"token":"60e216bd-d7ab-44f3-8bc7-31a0aacd75bc"}
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
        /**
         * customerId : 56286
         * mobile : 17372288373
         * name : 章鱼
         * hasPassword : 1
         * token : 60e216bd-d7ab-44f3-8bc7-31a0aacd75bc
         */

        private int customerId;
        private String mobile;
        private String name;
        private int hasPassword;
        private String token;

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHasPassword() {
            return hasPassword;
        }

        public void setHasPassword(int hasPassword) {
            this.hasPassword = hasPassword;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
