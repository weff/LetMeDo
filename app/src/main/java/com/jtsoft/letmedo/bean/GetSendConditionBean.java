package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/8/28.
 * 点击确认订单的收货地址，需要的获取配送费接口
 */

public class GetSendConditionBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1503893683625
     * response : {"deliveryInfo":{"deliveryPrice":11102,"distance":5553.9013,"shopId":1012,"shopName":"淮安1店"}}
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
         * deliveryInfo : {"deliveryPrice":11102,"distance":5553.9013,"shopId":1012,"shopName":"淮安1店"}
         */

        private DeliveryInfoBean deliveryInfo;

        public DeliveryInfoBean getDeliveryInfo() {
            return deliveryInfo;
        }

        public void setDeliveryInfo(DeliveryInfoBean deliveryInfo) {
            this.deliveryInfo = deliveryInfo;
        }

        public static class DeliveryInfoBean {
            /**
             * deliveryPrice : 11102
             * distance : 5553.9013
             * shopId : 1012
             * shopName : 淮安1店
             */

            private int deliveryPrice;
            private double distance;
            private int shopId;
            private String shopName;

            public int getDeliveryPrice() {
                return deliveryPrice;
            }

            public void setDeliveryPrice(int deliveryPrice) {
                this.deliveryPrice = deliveryPrice;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }
        }
    }
}
