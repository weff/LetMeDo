package com.jtsoft.letmedo.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/28.
 * 确认订单的可用优惠券接口
 */

public class GetUseCoupon {

    /**
     * code : 200
     * message : null
     * timestamp : 1504512573921
     * response : {"couponList":[{"couponReceiveId":97154,"userId":56289,"couponId":900,"couponName":"订单分享红包","scopeType":2,"couponPrice":2.82,"fullPrice":0,"useBeginTime":1503978883000,"useEndTime":1504583683000,"durationDay":7,"useStatus":0,"receiveTime":1503978883000,"businessType":"SHARE_ORDER_BUSINESS","businessId":776,"useType":0,"canUseId":173,"goodsName":null}]}
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
        private List<CouponListBean> couponList;

        public List<CouponListBean> getCouponList() {
            return couponList;
        }

        public void setCouponList(List<CouponListBean> couponList) {
            this.couponList = couponList;
        }

        public static class CouponListBean {
            /**
             * couponReceiveId : 97154
             * userId : 56289
             * couponId : 900
             * couponName : 订单分享红包
             * scopeType : 2
             * couponPrice : 2.82
             * fullPrice : 0
             * useBeginTime : 1503978883000
             * useEndTime : 1504583683000
             * durationDay : 7
             * useStatus : 0
             * receiveTime : 1503978883000
             * businessType : SHARE_ORDER_BUSINESS
             * businessId : 776
             * useType : 0
             * canUseId : 173
             * goodsName : null
             */

            private int couponReceiveId;
            private int userId;
            private int couponId;
            private String couponName;
            private int scopeType;
            private double couponPrice;
            private int fullPrice;
            private long useBeginTime;
            private long useEndTime;
            private int durationDay;
            private int useStatus;
            private long receiveTime;
            private String businessType;
            private int businessId;
            private int useType;
            private int canUseId;
            private String goodsName;

            public int getCouponReceiveId() {
                return couponReceiveId;
            }

            public void setCouponReceiveId(int couponReceiveId) {
                this.couponReceiveId = couponReceiveId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getCouponId() {
                return couponId;
            }

            public void setCouponId(int couponId) {
                this.couponId = couponId;
            }

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }

            public int getScopeType() {
                return scopeType;
            }

            public void setScopeType(int scopeType) {
                this.scopeType = scopeType;
            }

            public double getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(double couponPrice) {
                this.couponPrice = couponPrice;
            }

            public int getFullPrice() {
                return fullPrice;
            }

            public void setFullPrice(int fullPrice) {
                this.fullPrice = fullPrice;
            }

            public long getUseBeginTime() {
                return useBeginTime;
            }

            public void setUseBeginTime(long useBeginTime) {
                this.useBeginTime = useBeginTime;
            }

            public long getUseEndTime() {
                return useEndTime;
            }

            public void setUseEndTime(long useEndTime) {
                this.useEndTime = useEndTime;
            }

            public int getDurationDay() {
                return durationDay;
            }

            public void setDurationDay(int durationDay) {
                this.durationDay = durationDay;
            }

            public int getUseStatus() {
                return useStatus;
            }

            public void setUseStatus(int useStatus) {
                this.useStatus = useStatus;
            }

            public long getReceiveTime() {
                return receiveTime;
            }

            public void setReceiveTime(long receiveTime) {
                this.receiveTime = receiveTime;
            }

            public String getBusinessType() {
                return businessType;
            }

            public void setBusinessType(String businessType) {
                this.businessType = businessType;
            }

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public int getUseType() {
                return useType;
            }

            public void setUseType(int useType) {
                this.useType = useType;
            }

            public int getCanUseId() {
                return canUseId;
            }

            public void setCanUseId(int canUseId) {
                this.canUseId = canUseId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }
        }
    }
}
