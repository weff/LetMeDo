package com.jtsoft.letmedo.bean;

import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */
public class OutTimeBean {

    /**
     * code : 200
     * message : null
     * response : {"couponList":[{"couponReceiveId":1284,"userId":56289,"couponId":1090,"couponName":"手气优惠券","scopeType":2,"couponPrice":9.99,"fullPrice":0,"useBeginTime":1499720171000,"useEndTime":1500324971000,"durationDay":7,"useStatus":1,"receiveTime":1499720171000},{"couponReceiveId":1466,"userId":56289,"couponId":1111,"couponName":"手气优惠券","scopeType":2,"couponPrice":4.46,"fullPrice":0,"useBeginTime":1499808220000,"useEndTime":1500413020000,"durationDay":7,"useStatus":0,"receiveTime":1499808220000},{"couponReceiveId":1609,"userId":56289,"couponId":1114,"couponName":"手气优惠券","scopeType":2,"couponPrice":4.36,"fullPrice":0,"useBeginTime":1499866916000,"useEndTime":1500471716000,"durationDay":7,"useStatus":0,"receiveTime":1499866916000},{"couponReceiveId":1626,"userId":56289,"couponId":1116,"couponName":"手气优惠券","scopeType":2,"couponPrice":1.22,"fullPrice":0,"useBeginTime":1499869295000,"useEndTime":1500474095000,"durationDay":7,"useStatus":0,"receiveTime":1499869295000},{"couponReceiveId":1627,"userId":56289,"couponId":1117,"couponName":"手气优惠券","scopeType":2,"couponPrice":4.9,"fullPrice":0,"useBeginTime":1499869300000,"useEndTime":1500474100000,"durationDay":7,"useStatus":0,"receiveTime":1499869300000},{"couponReceiveId":1672,"userId":56289,"couponId":1118,"couponName":"手气优惠券","scopeType":2,"couponPrice":8.25,"fullPrice":0,"useBeginTime":1499879315000,"useEndTime":1500484115000,"durationDay":7,"useStatus":0,"receiveTime":1499879315000},{"couponReceiveId":1702,"userId":56289,"couponId":1119,"couponName":"手气优惠券","scopeType":2,"couponPrice":1.04,"fullPrice":0,"useBeginTime":1499883333000,"useEndTime":1500488133000,"durationDay":7,"useStatus":0,"receiveTime":1499883333000},{"couponReceiveId":1703,"userId":56289,"couponId":1121,"couponName":"手气优惠券","scopeType":2,"couponPrice":4.43,"fullPrice":0,"useBeginTime":1499883351000,"useEndTime":1500488151000,"durationDay":7,"useStatus":0,"receiveTime":1499883351000},{"couponReceiveId":2103,"userId":56289,"couponId":1150,"couponName":"手气优惠券","scopeType":2,"couponPrice":6.57,"fullPrice":0,"useBeginTime":1500312065000,"useEndTime":1500916865000,"durationDay":7,"useStatus":0,"receiveTime":1500312065000},{"couponReceiveId":2295,"userId":56289,"couponId":1163,"couponName":"手气优惠券","scopeType":2,"couponPrice":2.36,"fullPrice":0,"useBeginTime":1500495543000,"useEndTime":1501100343000,"durationDay":7,"useStatus":0,"receiveTime":1500495543000},{"couponReceiveId":2296,"userId":56289,"couponId":1053,"couponName":"手气优惠券","scopeType":2,"couponPrice":0.77,"fullPrice":0,"useBeginTime":1500495555000,"useEndTime":1501100355000,"durationDay":7,"useStatus":0,"receiveTime":1500495555000},{"couponReceiveId":5679,"userId":56289,"couponId":1440,"couponName":"手气优惠券","scopeType":2,"couponPrice":0.64,"fullPrice":0,"useBeginTime":1501232490000,"useEndTime":1501837290000,"durationDay":7,"useStatus":0,"receiveTime":1501232490000},{"couponReceiveId":8513,"userId":56289,"couponId":1021,"couponName":"手气优惠券","scopeType":2,"couponPrice":2.19,"fullPrice":0,"useBeginTime":1501819307000,"useEndTime":1502424107000,"durationDay":7,"useStatus":0,"receiveTime":1501819307000}]}
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
        private List<CouponListBean> couponList;

        public List<CouponListBean> getCouponList() {
            return couponList;
        }

        public void setCouponList(List<CouponListBean> couponList) {
            this.couponList = couponList;
        }

        public static class CouponListBean {
            /**
             * couponReceiveId : 1284
             * userId : 56289
             * couponId : 1090
             * couponName : 手气优惠券
             * scopeType : 2
             * couponPrice : 9.99
             * fullPrice : 0.0
             * useBeginTime : 1499720171000
             * useEndTime : 1500324971000
             * durationDay : 7
             * useStatus : 1
             * receiveTime : 1499720171000
             */

            private int couponReceiveId;
            private int userId;
            private int couponId;
            private String couponName;
            private int scopeType;
            private double couponPrice;
            private double fullPrice;
            private long useBeginTime;
            private long useEndTime;
            private int durationDay;
            private int useStatus;
            private long receiveTime;

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

            public double getFullPrice() {
                return fullPrice;
            }

            public void setFullPrice(double fullPrice) {
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
        }
    }
}
