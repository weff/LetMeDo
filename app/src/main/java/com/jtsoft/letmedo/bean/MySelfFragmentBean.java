package com.jtsoft.letmedo.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/8.
 * 自提订单接口
 */
public class MySelfFragmentBean {


    /**
     * code : 200
     * message : null
     * timestamp : 1504604248190
     * response : {"orderList":[{"orderId":835,"orderCode":"20170905162507587","orderStatus":1,"userId":56289,"orderPrice":0.01,"orderOldPrice":0,"expressPrice":0,"couponReceiveId":null,"couponPrice":0,"payTime":1504599915000,"payType":1,"deliveryLocationId":null,"deliveryType":1,"deliveryAddress":"建邺区新安江街99号","contactMobile":"18319628135","contactPersion":"chen","longitude":118.731236,"latitude":32.003269,"distance":0,"shopId":1018,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":null,"createTime":1504599908000,"updateTime":1504599914000,"orderGoodsList":[]}],"page":{"pageNo":1,"pageSize":10,"totalCount":11,"totalPage":2}}
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
         * orderList : [{"orderId":835,"orderCode":"20170905162507587","orderStatus":1,"userId":56289,"orderPrice":0.01,"orderOldPrice":0,"expressPrice":0,"couponReceiveId":null,"couponPrice":0,"payTime":1504599915000,"payType":1,"deliveryLocationId":null,"deliveryType":1,"deliveryAddress":"建邺区新安江街99号","contactMobile":"18319628135","contactPersion":"chen","longitude":118.731236,"latitude":32.003269,"distance":0,"shopId":1018,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":null,"createTime":1504599908000,"updateTime":1504599914000,"orderGoodsList":[]}]
         * page : {"pageNo":1,"pageSize":10,"totalCount":11,"totalPage":2}
         */

        private PageBean page;
        private List<OrderListBean> orderList;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class PageBean {
            /**
             * pageNo : 1
             * pageSize : 10
             * totalCount : 11
             * totalPage : 2
             */

            private int pageNo;
            private int pageSize;
            private int totalCount;
            private int totalPage;

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }
        }

        public static class OrderListBean {
            /**
             * orderId : 835
             * orderCode : 20170905162507587
             * orderStatus : 1
             * userId : 56289
             * orderPrice : 0.01
             * orderOldPrice : 0
             * expressPrice : 0
             * couponReceiveId : null
             * couponPrice : 0
             * payTime : 1504599915000
             * payType : 1
             * deliveryLocationId : null
             * deliveryType : 1
             * deliveryAddress : 建邺区新安江街99号
             * contactMobile : 18319628135
             * contactPersion : chen
             * longitude : 118.731236
             * latitude : 32.003269
             * distance : 0
             * shopId : 1018
             * source : WECHAT
             * delStatus : 0
             * deliveryTime : null
             * remarke : null
             * createTime : 1504599908000
             * updateTime : 1504599914000
             * orderGoodsList : []
             */

            private int orderId;
            private String orderCode;
            private int orderStatus;
            private int userId;
            private double orderPrice;
            private int orderOldPrice;
            private int expressPrice;
            private Object couponReceiveId;
            private int couponPrice;
            private long payTime;
            private int payType;
            private Object deliveryLocationId;
            private int deliveryType;
            private String deliveryAddress;
            private String contactMobile;
            private String contactPersion;
            private double longitude;
            private double latitude;
            private int distance;
            private int shopId;
            private String source;
            private int delStatus;
            private Object deliveryTime;
            private Object remarke;
            private long createTime;
            private long updateTime;
            private List<?> orderGoodsList;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public double getOrderPrice() {
                return orderPrice;
            }

            public void setOrderPrice(double orderPrice) {
                this.orderPrice = orderPrice;
            }

            public int getOrderOldPrice() {
                return orderOldPrice;
            }

            public void setOrderOldPrice(int orderOldPrice) {
                this.orderOldPrice = orderOldPrice;
            }

            public int getExpressPrice() {
                return expressPrice;
            }

            public void setExpressPrice(int expressPrice) {
                this.expressPrice = expressPrice;
            }

            public Object getCouponReceiveId() {
                return couponReceiveId;
            }

            public void setCouponReceiveId(Object couponReceiveId) {
                this.couponReceiveId = couponReceiveId;
            }

            public int getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(int couponPrice) {
                this.couponPrice = couponPrice;
            }

            public long getPayTime() {
                return payTime;
            }

            public void setPayTime(long payTime) {
                this.payTime = payTime;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public Object getDeliveryLocationId() {
                return deliveryLocationId;
            }

            public void setDeliveryLocationId(Object deliveryLocationId) {
                this.deliveryLocationId = deliveryLocationId;
            }

            public int getDeliveryType() {
                return deliveryType;
            }

            public void setDeliveryType(int deliveryType) {
                this.deliveryType = deliveryType;
            }

            public String getDeliveryAddress() {
                return deliveryAddress;
            }

            public void setDeliveryAddress(String deliveryAddress) {
                this.deliveryAddress = deliveryAddress;
            }

            public String getContactMobile() {
                return contactMobile;
            }

            public void setContactMobile(String contactMobile) {
                this.contactMobile = contactMobile;
            }

            public String getContactPersion() {
                return contactPersion;
            }

            public void setContactPersion(String contactPersion) {
                this.contactPersion = contactPersion;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public int getDelStatus() {
                return delStatus;
            }

            public void setDelStatus(int delStatus) {
                this.delStatus = delStatus;
            }

            public Object getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(Object deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public Object getRemarke() {
                return remarke;
            }

            public void setRemarke(Object remarke) {
                this.remarke = remarke;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public List<?> getOrderGoodsList() {
                return orderGoodsList;
            }

            public void setOrderGoodsList(List<?> orderGoodsList) {
                this.orderGoodsList = orderGoodsList;
            }
        }
    }
}
