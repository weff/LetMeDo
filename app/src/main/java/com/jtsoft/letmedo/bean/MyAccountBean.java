package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/9/11.
 * 账户余额接口
 */

public class MyAccountBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1505113004773
     * response : {"deposit":{"depositId":24599,"customerId":56289,"preDeposit":9.99989868E7,"freezeDeposit":0}}
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
         * deposit : {"depositId":24599,"customerId":56289,"preDeposit":9.99989868E7,"freezeDeposit":0}
         */

        private DepositBean deposit;

        public DepositBean getDeposit() {
            return deposit;
        }

        public void setDeposit(DepositBean deposit) {
            this.deposit = deposit;
        }

        public static class DepositBean {
            /**
             * depositId : 24599
             * customerId : 56289
             * preDeposit : 9.99989868E7
             * freezeDeposit : 0
             */

            private int depositId;
            private int customerId;
            private double preDeposit;
            private int freezeDeposit;

            public int getDepositId() {
                return depositId;
            }

            public void setDepositId(int depositId) {
                this.depositId = depositId;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public double getPreDeposit() {
                return preDeposit;
            }

            public void setPreDeposit(double preDeposit) {
                this.preDeposit = preDeposit;
            }

            public int getFreezeDeposit() {
                return freezeDeposit;
            }

            public void setFreezeDeposit(int freezeDeposit) {
                this.freezeDeposit = freezeDeposit;
            }
        }
    }
}
