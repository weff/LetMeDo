package com.jtsoft.letmedo. bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */
public class ShoppingCartBean implements Serializable{



    /**
     * code : 200
     * message : null
     * currentTimeMillis : 1502956255681
     * response : {"shoppingCartList":[{"cartId":247,"goodsId":171,"cuntomerId":56289,"num":4,"delStatus":0,"updateTime":1502762366000,"createTime":1502762357000,"goods":{"goodsId":171,"name":"B级-新沂水蜜桃","param":"[{\"key\":\"产地\",\"value\":\"徐州\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2粒(约500g)","price":9.9,"oldPrice":12.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501744916719.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501744897722.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745019000,"updateTime":1502157782000,"detail":null}},{"cartId":349,"goodsId":179,"cuntomerId":56289,"num":1,"delStatus":0,"updateTime":1502942412000,"createTime":1502942412000,"goods":{"goodsId":179,"name":"A级-智利红提","param":"[{\"key\":\"产地\",\"value\":\"智利\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"500-550g","price":14.9,"oldPrice":19.9,"deliveryPrice":null,"tag":"","cateId":1060,"image":"http://or6975drq.bkt.clouddn.com/1501745881764.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745868296.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745983000,"updateTime":1501745983000,"detail":null}}]}
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
        private List<ShoppingCartListBean> shoppingCartList;

        public List<ShoppingCartListBean> getShoppingCartList() {
            return shoppingCartList;
        }

        public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
            this.shoppingCartList = shoppingCartList;
        }

        public static class ShoppingCartListBean implements Serializable{
            /**
             * cartId : 247
             * goodsId : 171
             * cuntomerId : 56289
             * num : 4
             * delStatus : 0
             * updateTime : 1502762366000
             * createTime : 1502762357000
             * goods : {"goodsId":171,"name":"B级-新沂水蜜桃","param":"[{\"key\":\"产地\",\"value\":\"徐州\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2粒(约500g)","price":9.9,"oldPrice":12.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501744916719.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501744897722.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745019000,"updateTime":1502157782000,"detail":null}
             */

            private int cartId;
            private int goodsId;
            private int cuntomerId;
            private int num;
            private int delStatus;
            private long updateTime;
            private long createTime;
            private GoodsBean goods;
            private boolean isChoosed ;

            public boolean isChoosed() {
                return isChoosed;
            }

            public void setChoosed(boolean choosed) {
                isChoosed = choosed;
            }

            public int getCartId() {
                return cartId;
            }

            public void setCartId(int cartId) {
                this.cartId = cartId;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getCuntomerId() {
                return cuntomerId;
            }

            public void setCuntomerId(int cuntomerId) {
                this.cuntomerId = cuntomerId;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getDelStatus() {
                return delStatus;
            }

            public void setDelStatus(int delStatus) {
                this.delStatus = delStatus;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public GoodsBean getGoods() {
                return goods;
            }//看这个，返回的是goods

            public void setGoods(GoodsBean goods) {
                this.goods = goods;
            }

            public static class GoodsBean implements Serializable{
                /**
                 * goodsId : 171
                 * name : B级-新沂水蜜桃
                 * param : [{"key":"产地","value":"徐州"},{"key":"包装","value":"份"}]
                 * weight : null
                 * spec : 2粒(约500g)
                 * price : 9.9
                 * oldPrice : 12.8
                 * deliveryPrice : null
                 * tag :
                 * cateId : 1064
                 * image : http://or6975drq.bkt.clouddn.com/1501744916719.jpg
                 * thumImage : http://or6975drq.bkt.clouddn.com/1501744897722.jpg
                 * stockNum : 999
                 * saleNum : 0
                 * grade : null
                 * shopId : 0
                 * status : 1
                 * delStatus : 0
                 * createTime : 1501745019000
                 * updateTime : 1502157782000
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
