package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/9/11.
 * 商品详情接口
 */

public class GoodsDetailBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1505114908004
     * response : {"goods":{"goodsId":103,"name":"兰格瑞小麦啤酒","param":"[{\"key\":\"产地\",\"value\":\"德国\"},{\"key\":\"包装\",\"value\":\"桶\"}]","weight":null,"spec":"5L","price":108,"oldPrice":120,"deliveryPrice":null,"tag":"","cateId":1068,"image":"http://or6975drq.bkt.clouddn.com/1501739169645.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501739317148.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501739468000,"updateTime":1501747248000,"detail":"<p><img src=\"http://or6975drq.bkt.clouddn.com/1501739320767.jpg\"><\/p>"}}
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
         * goods : {"goodsId":103,"name":"兰格瑞小麦啤酒","param":"[{\"key\":\"产地\",\"value\":\"德国\"},{\"key\":\"包装\",\"value\":\"桶\"}]","weight":null,"spec":"5L","price":108,"oldPrice":120,"deliveryPrice":null,"tag":"","cateId":1068,"image":"http://or6975drq.bkt.clouddn.com/1501739169645.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501739317148.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501739468000,"updateTime":1501747248000,"detail":"<p><img src=\"http://or6975drq.bkt.clouddn.com/1501739320767.jpg\"><\/p>"}
         */

        private GoodsBean goods;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goodsId : 103
             * name : 兰格瑞小麦啤酒
             * param : [{"key":"产地","value":"德国"},{"key":"包装","value":"桶"}]
             * weight : null
             * spec : 5L
             * price : 108
             * oldPrice : 120
             * deliveryPrice : null
             * tag :
             * cateId : 1068
             * image : http://or6975drq.bkt.clouddn.com/1501739169645.jpg
             * thumImage : http://or6975drq.bkt.clouddn.com/1501739317148.jpg
             * stockNum : 999
             * saleNum : 0
             * grade : null
             * shopId : 0
             * status : 1
             * delStatus : 0
             * createTime : 1501739468000
             * updateTime : 1501747248000
             * detail : <p><img src="http://or6975drq.bkt.clouddn.com/1501739320767.jpg"></p>
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
            private String detail;

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

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }
        }
    }
}
