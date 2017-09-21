package com.jtsoft.letmedo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class BillOrderBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1504858811064
     * response : {"orderList":[{"orderId":874,"orderCode":"20170908161608496","orderStatus":0,"userId":56289,"orderPrice":39.8,"orderOldPrice":39.8,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":null,"payType":null,"deliveryLocationId":183,"deliveryType":0,"deliveryAddress":"江苏南京浦口柳洲东路(地铁站)","contactMobile":"18319628135","contactPersion":"chen","longitude":118.746237,"latitude":32.139965,"distance":900.9000000000001,"shopId":1001,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504858568000,"updateTime":1504858568000,"orderGoodsList":[{"orderGoodsId":1084,"orderId":874,"goodsId":182,"num":1,"createTime":1504858568000,"updateTime":1504858568000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}},{"orderGoodsId":1085,"orderId":874,"goodsId":117,"num":1,"createTime":1504858568000,"updateTime":1504858568000,"goods":{"goodsId":117,"name":"A级-8424西瓜","param":"[{\"key\":\"产地\",\"value\":\"上海\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"1个(约4000g)","price":15.9,"oldPrice":19.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501740177314.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501740274507.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501740405000,"updateTime":1501740405000,"detail":null}}]}],"page":{"pageNo":1,"pageSize":10,"totalCount":2,"totalPage":1}}
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
         * orderList : [{"orderId":874,"orderCode":"20170908161608496","orderStatus":0,"userId":56289,"orderPrice":39.8,"orderOldPrice":39.8,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":null,"payType":null,"deliveryLocationId":183,"deliveryType":0,"deliveryAddress":"江苏南京浦口柳洲东路(地铁站)","contactMobile":"18319628135","contactPersion":"chen","longitude":118.746237,"latitude":32.139965,"distance":900.9000000000001,"shopId":1001,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504858568000,"updateTime":1504858568000,"orderGoodsList":[{"orderGoodsId":1084,"orderId":874,"goodsId":182,"num":1,"createTime":1504858568000,"updateTime":1504858568000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}},{"orderGoodsId":1085,"orderId":874,"goodsId":117,"num":1,"createTime":1504858568000,"updateTime":1504858568000,"goods":{"goodsId":117,"name":"A级-8424西瓜","param":"[{\"key\":\"产地\",\"value\":\"上海\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"1个(约4000g)","price":15.9,"oldPrice":19.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501740177314.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501740274507.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501740405000,"updateTime":1501740405000,"detail":null}}]}]
         * page : {"pageNo":1,"pageSize":10,"totalCount":2,"totalPage":1}
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
             * totalCount : 2
             * totalPage : 1
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
             * orderId : 874
             * orderCode : 20170908161608496
             * orderStatus : 0
             * userId : 56289
             * orderPrice : 39.8
             * orderOldPrice : 39.8
             * expressPrice : 4
             * couponReceiveId : null
             * couponPrice : 0
             * payTime : null
             * payType : null
             * deliveryLocationId : 183
             * deliveryType : 0
             * deliveryAddress : 江苏南京浦口柳洲东路(地铁站)
             * contactMobile : 18319628135
             * contactPersion : chen
             * longitude : 118.746237
             * latitude : 32.139965
             * distance : 900.9000000000001
             * shopId : 1001
             * source : WECHAT
             * delStatus : 0
             * deliveryTime : null
             * remarke :
             * createTime : 1504858568000
             * updateTime : 1504858568000
             * orderGoodsList : [{"orderGoodsId":1084,"orderId":874,"goodsId":182,"num":1,"createTime":1504858568000,"updateTime":1504858568000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}},{"orderGoodsId":1085,"orderId":874,"goodsId":117,"num":1,"createTime":1504858568000,"updateTime":1504858568000,"goods":{"goodsId":117,"name":"A级-8424西瓜","param":"[{\"key\":\"产地\",\"value\":\"上海\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"1个(约4000g)","price":15.9,"oldPrice":19.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501740177314.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501740274507.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501740405000,"updateTime":1501740405000,"detail":null}}]
             */

            private int orderId;
            private String orderCode;
            private int orderStatus;
            private int userId;
            private double orderPrice;
            private double orderOldPrice;
            private int expressPrice;
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
            private String source;
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

            public void setOrderPrice(double orderPrice) {
                this.orderPrice = orderPrice;
            }

            public double getOrderOldPrice() {
                return orderOldPrice;
            }

            public void setOrderOldPrice(double orderOldPrice) {
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

            public double getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(double couponPrice) {
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
                 * orderGoodsId : 1084
                 * orderId : 874
                 * goodsId : 182
                 * num : 1
                 * createTime : 1504858568000
                 * updateTime : 1504858568000
                 * goods : {"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}
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
                     * goodsId : 182
                     * name : B级-冬枣
                     * param : [{"key":"产地","value":"山东"},{"key":"包装","value":"份"}]
                     * weight : null
                     * spec : 约500g
                     * price : 19.9
                     * oldPrice : 25.8
                     * deliveryPrice : null
                     * tag :
                     * cateId : 1064
                     * image : http://or6975drq.bkt.clouddn.com/1501746075298.jpg
                     * thumImage : http://or6975drq.bkt.clouddn.com/1501746139596.png
                     * stockNum : 999
                     * saleNum : 0
                     * grade : null
                     * shopId : 0
                     * status : 1
                     * delStatus : 0
                     * createTime : 1501746339000
                     * updateTime : 1501746339000
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

                    public void setPrice(double price) {
                        this.price = price;
                    }

                    public double getOldPrice() {
                        return oldPrice;
                    }

                    public void setOldPrice(double oldPrice) {
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
