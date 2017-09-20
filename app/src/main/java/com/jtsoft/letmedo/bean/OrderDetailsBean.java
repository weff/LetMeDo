package com.jtsoft.letmedo.bean;

import java.util.List;

/**
 * Created by admin on 2017/9/4.
 * 获取订单详情的接口
 */

public class OrderDetailsBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1504490399542
     * response : {"order":{"orderId":193,"orderCode":"20170728140006390351","orderStatus":4,"userId":53781,"orderPrice":4325,"orderOldPrice":4325,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":null,"payType":null,"deliveryLocationId":75,"deliveryType":0,"deliveryAddress":"江苏南京浦口天润城","contactMobile":"13815875611","contactPersion":"测试账号","longitude":118.74091662941923,"latitude":32.149332176182966,"distance":1426.1999999999998,"shopId":0,"source":null,"delStatus":0,"deliveryTime":null,"remarke":"","createTime":1501221607000,"updateTime":1502519197000,"orderGoodsList":[{"orderGoodsId":102,"orderId":193,"goodsId":29,"num":1,"createTime":1501221607000,"updateTime":1501221607000,"goods":{"goodsId":29,"name":"测试商品","param":"[{\"key\":\"产地\",\"value\":\"china\"}]","weight":null,"spec":"123","price":4321,"oldPrice":1324,"deliveryPrice":null,"tag":"1","cateId":2,"image":"http://or6975drq.bkt.clouddn.com/1498203441328.png","thumImage":"http://or6975drq.bkt.clouddn.com/1498203444732.png","stockNum":250,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":1,"createTime":1497966589000,"updateTime":1501667376000,"detail":null}}]}}
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
         * order : {"orderId":193,"orderCode":"20170728140006390351","orderStatus":4,"userId":53781,"orderPrice":4325,"orderOldPrice":4325,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":null,"payType":null,"deliveryLocationId":75,"deliveryType":0,"deliveryAddress":"江苏南京浦口天润城","contactMobile":"13815875611","contactPersion":"测试账号","longitude":118.74091662941923,"latitude":32.149332176182966,"distance":1426.1999999999998,"shopId":0,"source":null,"delStatus":0,"deliveryTime":null,"remarke":"","createTime":1501221607000,"updateTime":1502519197000,"orderGoodsList":[{"orderGoodsId":102,"orderId":193,"goodsId":29,"num":1,"createTime":1501221607000,"updateTime":1501221607000,"goods":{"goodsId":29,"name":"测试商品","param":"[{\"key\":\"产地\",\"value\":\"china\"}]","weight":null,"spec":"123","price":4321,"oldPrice":1324,"deliveryPrice":null,"tag":"1","cateId":2,"image":"http://or6975drq.bkt.clouddn.com/1498203441328.png","thumImage":"http://or6975drq.bkt.clouddn.com/1498203444732.png","stockNum":250,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":1,"createTime":1497966589000,"updateTime":1501667376000,"detail":null}}]}
         */

        private OrderBean order;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * orderId : 193
             * orderCode : 20170728140006390351
             * orderStatus : 4
             * userId : 53781
             * orderPrice : 4325
             * orderOldPrice : 4325
             * expressPrice : 4
             * couponReceiveId : null
             * couponPrice : 0
             * payTime : null
             * payType : null
             * deliveryLocationId : 75
             * deliveryType : 0
             * deliveryAddress : 江苏南京浦口天润城
             * contactMobile : 13815875611
             * contactPersion : 测试账号
             * longitude : 118.74091662941923
             * latitude : 32.149332176182966
             * distance : 1426.1999999999998
             * shopId : 0
             * source : null
             * delStatus : 0
             * deliveryTime : null
             * remarke :
             * createTime : 1501221607000
             * updateTime : 1502519197000
             * orderGoodsList : [{"orderGoodsId":102,"orderId":193,"goodsId":29,"num":1,"createTime":1501221607000,"updateTime":1501221607000,"goods":{"goodsId":29,"name":"测试商品","param":"[{\"key\":\"产地\",\"value\":\"china\"}]","weight":null,"spec":"123","price":4321,"oldPrice":1324,"deliveryPrice":null,"tag":"1","cateId":2,"image":"http://or6975drq.bkt.clouddn.com/1498203441328.png","thumImage":"http://or6975drq.bkt.clouddn.com/1498203444732.png","stockNum":250,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":1,"createTime":1497966589000,"updateTime":1501667376000,"detail":null}}]
             */

            private int orderId;
            private String orderCode;
            private int orderStatus;
            private int userId;
            private double orderPrice;
            private double orderOldPrice;
            private double expressPrice;
            private Object couponReceiveId;
            private double couponPrice;
            private Object payTime;
            private Object payType;
            private int deliveryLocationId;
            private int deliveryType;
            private String deliveryAddress;
            private String contactMobile;
            private String contactPersion;
            private double longitude;
            private double latitude;
            private double distance;
            private int shopId;
            private Object source;
            private int delStatus;
            private Object deliveryTime;
            private String remarke;
            private long createTime;
            private long updateTime;
            private List<OrderGoodsListBean> orderGoodsList;

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

            public void setOrderPrice(int orderPrice) {
                this.orderPrice = orderPrice;
            }

            public double getOrderOldPrice() {
                return orderOldPrice;
            }

            public void setOrderOldPrice(int orderOldPrice) {
                this.orderOldPrice = orderOldPrice;
            }

            public double getExpressPrice() {
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

            public double getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(int couponPrice) {
                this.couponPrice = couponPrice;
            }

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public Object getPayType() {
                return payType;
            }

            public void setPayType(Object payType) {
                this.payType = payType;
            }

            public int getDeliveryLocationId() {
                return deliveryLocationId;
            }

            public void setDeliveryLocationId(int deliveryLocationId) {
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

            public Object getSource() {
                return source;
            }

            public void setSource(Object source) {
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

            public String getRemarke() {
                return remarke;
            }

            public void setRemarke(String remarke) {
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

            public List<OrderGoodsListBean> getOrderGoodsList() {
                return orderGoodsList;
            }

            public void setOrderGoodsList(List<OrderGoodsListBean> orderGoodsList) {
                this.orderGoodsList = orderGoodsList;
            }

            public static class OrderGoodsListBean {
                /**
                 * orderGoodsId : 102
                 * orderId : 193
                 * goodsId : 29
                 * num : 1
                 * createTime : 1501221607000
                 * updateTime : 1501221607000
                 * goods : {"goodsId":29,"name":"测试商品","param":"[{\"key\":\"产地\",\"value\":\"china\"}]","weight":null,"spec":"123","price":4321,"oldPrice":1324,"deliveryPrice":null,"tag":"1","cateId":2,"image":"http://or6975drq.bkt.clouddn.com/1498203441328.png","thumImage":"http://or6975drq.bkt.clouddn.com/1498203444732.png","stockNum":250,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":1,"createTime":1497966589000,"updateTime":1501667376000,"detail":null}
                 */

                private int orderGoodsId;
                private int orderId;
                private int goodsId;
                private int num;
                private long createTime;
                private long updateTime;
                private GoodsBean goods;

                public int getOrderGoodsId() {
                    return orderGoodsId;
                }

                public void setOrderGoodsId(int orderGoodsId) {
                    this.orderGoodsId = orderGoodsId;
                }

                public int getOrderId() {
                    return orderId;
                }

                public void setOrderId(int orderId) {
                    this.orderId = orderId;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
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

                public GoodsBean getGoods() {
                    return goods;
                }

                public void setGoods(GoodsBean goods) {
                    this.goods = goods;
                }

                public static class GoodsBean {
                    /**
                     * goodsId : 29
                     * name : 测试商品
                     * param : [{"key":"产地","value":"china"}]
                     * weight : null
                     * spec : 123
                     * price : 4321
                     * oldPrice : 1324
                     * deliveryPrice : null
                     * tag : 1
                     * cateId : 2
                     * image : http://or6975drq.bkt.clouddn.com/1498203441328.png
                     * thumImage : http://or6975drq.bkt.clouddn.com/1498203444732.png
                     * stockNum : 250
                     * saleNum : 0
                     * grade : null
                     * shopId : 0
                     * status : 1
                     * delStatus : 1
                     * createTime : 1497966589000
                     * updateTime : 1501667376000
                     * detail : null
                     */

                    private int goodsId;
                    private String name;
                    private String param;
                    private Object weight;
                    private String spec;
                    private double price;
                    private double oldPrice;
                    private Object deliveryPrice;
                    private String tag;
                    private int cateId;
                    private String image;
                    private String thumImage;
                    private int stockNum;
                    private int saleNum;
                    private Object grade;
                    private int shopId;
                    private int status;
                    private int delStatus;
                    private long createTime;
                    private long updateTime;
                    private Object detail;

                    public int getGoodsId() {
                        return goodsId;
                    }

                    public void setGoodsId(int goodsId) {
                        this.goodsId = goodsId;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getParam() {
                        return param;
                    }

                    public void setParam(String param) {
                        this.param = param;
                    }

                    public Object getWeight() {
                        return weight;
                    }

                    public void setWeight(Object weight) {
                        this.weight = weight;
                    }

                    public String getSpec() {
                        return spec;
                    }

                    public void setSpec(String spec) {
                        this.spec = spec;
                    }

                    public double getPrice() {
                        return price;
                    }

                    public void setPrice(int price) {
                        this.price = price;
                    }

                    public double getOldPrice() {
                        return oldPrice;
                    }

                    public void setOldPrice(int oldPrice) {
                        this.oldPrice = oldPrice;
                    }

                    public Object getDeliveryPrice() {
                        return deliveryPrice;
                    }

                    public void setDeliveryPrice(Object deliveryPrice) {
                        this.deliveryPrice = deliveryPrice;
                    }

                    public String getTag() {
                        return tag;
                    }

                    public void setTag(String tag) {
                        this.tag = tag;
                    }

                    public int getCateId() {
                        return cateId;
                    }

                    public void setCateId(int cateId) {
                        this.cateId = cateId;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getThumImage() {
                        return thumImage;
                    }

                    public void setThumImage(String thumImage) {
                        this.thumImage = thumImage;
                    }

                    public int getStockNum() {
                        return stockNum;
                    }

                    public void setStockNum(int stockNum) {
                        this.stockNum = stockNum;
                    }

                    public int getSaleNum() {
                        return saleNum;
                    }

                    public void setSaleNum(int saleNum) {
                        this.saleNum = saleNum;
                    }

                    public Object getGrade() {
                        return grade;
                    }

                    public void setGrade(Object grade) {
                        this.grade = grade;
                    }

                    public int getShopId() {
                        return shopId;
                    }

                    public void setShopId(int shopId) {
                        this.shopId = shopId;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getDelStatus() {
                        return delStatus;
                    }

                    public void setDelStatus(int delStatus) {
                        this.delStatus = delStatus;
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

                    public Object getDetail() {
                        return detail;
                    }

                    public void setDetail(Object detail) {
                        this.detail = detail;
                    }
                }
            }
        }
    }
}
