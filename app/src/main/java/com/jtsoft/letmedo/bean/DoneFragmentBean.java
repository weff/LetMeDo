package com.jtsoft.letmedo.bean;

import java.util.List;

/**
 * Created by admin on 2017/9/5.
 * 已完成订单接口
 */

public class DoneFragmentBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1504605978906
     * response : {"orderList":[{"orderId":803,"orderCode":"20170902143700447","orderStatus":3,"userId":56289,"orderPrice":44.7,"orderOldPrice":44.7,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504334246000,"payType":3,"deliveryLocationId":133,"deliveryType":0,"deliveryAddress":"江苏南京浦口天润城(百润路) 2号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.734322,"latitude":32.143663,"distance":324.2,"shopId":1001,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504334221000,"updateTime":1504421986000,"orderGoodsList":[{"orderGoodsId":990,"orderId":803,"goodsId":171,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":171,"name":"B级-新沂水蜜桃","param":"[{\"key\":\"产地\",\"value\":\"徐州\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2粒(约500g)","price":9.9,"oldPrice":12.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501744916719.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501744897722.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745019000,"updateTime":1502157782000,"detail":null}},{"orderGoodsId":991,"orderId":803,"goodsId":117,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":117,"name":"A级-8424西瓜","param":"[{\"key\":\"产地\",\"value\":\"上海\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"1个(约4000g)","price":15.9,"oldPrice":19.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501740177314.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501740274507.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501740405000,"updateTime":1501740405000,"detail":null}},{"orderGoodsId":992,"orderId":803,"goodsId":179,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":179,"name":"A级-智利红提","param":"[{\"key\":\"产地\",\"value\":\"智利\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"500-550g","price":14.9,"oldPrice":19.9,"deliveryPrice":null,"tag":"","cateId":1060,"image":"http://or6975drq.bkt.clouddn.com/1501745881764.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745868296.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745983000,"updateTime":1501745983000,"detail":null}}]},{"orderId":798,"orderCode":"20170901174050435","orderStatus":3,"userId":56289,"orderPrice":43.6,"orderOldPrice":43.6,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504258865000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504258850000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":982,"orderId":798,"goodsId":178,"num":4,"createTime":1504258850000,"updateTime":1504258850000,"goods":{"goodsId":178,"name":"A级-新西兰加力果","param":"[{\"key\":\"产地\",\"value\":\"新西兰\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2个(约400g)","price":9.9,"oldPrice":13.8,"deliveryPrice":null,"tag":"","cateId":1062,"image":"http://or6975drq.bkt.clouddn.com/1501745694999.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745778572.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745892000,"updateTime":1501745892000,"detail":null}}]},{"orderId":797,"orderCode":"20170901173627365","orderStatus":3,"userId":56289,"orderPrice":23.9,"orderOldPrice":23.9,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504258601000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504258588000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":981,"orderId":797,"goodsId":182,"num":1,"createTime":1504258588000,"updateTime":1504258588000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}}]},{"orderId":796,"orderCode":"20170901173217790","orderStatus":3,"userId":56289,"orderPrice":33.7,"orderOldPrice":33.7,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504258352000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504258338000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":980,"orderId":796,"goodsId":178,"num":3,"createTime":1504258338000,"updateTime":1504258338000,"goods":{"goodsId":178,"name":"A级-新西兰加力果","param":"[{\"key\":\"产地\",\"value\":\"新西兰\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2个(约400g)","price":9.9,"oldPrice":13.8,"deliveryPrice":null,"tag":"","cateId":1062,"image":"http://or6975drq.bkt.clouddn.com/1501745694999.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745778572.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745892000,"updateTime":1501745892000,"detail":null}}]},{"orderId":795,"orderCode":"20170901172757209","orderStatus":3,"userId":56289,"orderPrice":19.9,"orderOldPrice":19.9,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504258091000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504258078000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":979,"orderId":795,"goodsId":117,"num":1,"createTime":1504258078000,"updateTime":1504258078000,"goods":{"goodsId":117,"name":"A级-8424西瓜","param":"[{\"key\":\"产地\",\"value\":\"上海\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"1个(约4000g)","price":15.9,"oldPrice":19.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501740177314.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501740274507.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501740405000,"updateTime":1501740405000,"detail":null}}]},{"orderId":794,"orderCode":"20170901172058317","orderStatus":3,"userId":56289,"orderPrice":33.7,"orderOldPrice":33.7,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504257963000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504257658000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":978,"orderId":794,"goodsId":128,"num":1,"createTime":1504257658000,"updateTime":1504257658000,"goods":{"goodsId":128,"name":"德博黑啤酒","param":"[{\"key\":\"产地\",\"value\":\"德国\"},{\"key\":\"包装\",\"value\":\"听\"}]","weight":null,"spec":"3听（500ml*3）","price":29.7,"oldPrice":35,"deliveryPrice":null,"tag":"","cateId":1068,"image":"http://or6975drq.bkt.clouddn.com/1501740896610.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501741980707.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501742138000,"updateTime":1501747370000,"detail":null}}]},{"orderId":779,"orderCode":"20170901145230651","orderStatus":3,"userId":56289,"orderPrice":31.6,"orderOldPrice":31.6,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504248764000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504248750000,"updateTime":1504335586000,"orderGoodsList":[{"orderGoodsId":963,"orderId":779,"goodsId":180,"num":2,"createTime":1504248750000,"updateTime":1504248750000,"goods":{"goodsId":180,"name":"A级-泰国椰青","param":"[{\"key\":\"产地\",\"value\":\"泰国\"},{\"key\":\"包装\",\"value\":\"个\"}]","weight":null,"spec":"1个","price":13.8,"oldPrice":19.9,"deliveryPrice":null,"tag":"","cateId":3,"image":"http://or6975drq.bkt.clouddn.com/1501745890154.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745958357.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746068000,"updateTime":1502156523000,"detail":null}}]},{"orderId":778,"orderCode":"20170901144544485","orderStatus":3,"userId":56289,"orderPrice":23.8,"orderOldPrice":23.8,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504248358000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504248345000,"updateTime":1504335586000,"orderGoodsList":[{"orderGoodsId":962,"orderId":778,"goodsId":171,"num":2,"createTime":1504248345000,"updateTime":1504248345000,"goods":{"goodsId":171,"name":"B级-新沂水蜜桃","param":"[{\"key\":\"产地\",\"value\":\"徐州\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2粒(约500g)","price":9.9,"oldPrice":12.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501744916719.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501744897722.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745019000,"updateTime":1502157782000,"detail":null}}]},{"orderId":777,"orderCode":"20170901144100015","orderStatus":3,"userId":56289,"orderPrice":43.8,"orderOldPrice":43.8,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504248071000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504248061000,"updateTime":1504335586000,"orderGoodsList":[{"orderGoodsId":961,"orderId":777,"goodsId":182,"num":2,"createTime":1504248061000,"updateTime":1504248061000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}}]},{"orderId":775,"orderCode":"20170901143617193","orderStatus":3,"userId":56289,"orderPrice":43.8,"orderOldPrice":43.8,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504247790000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504247777000,"updateTime":1504335586000,"orderGoodsList":[{"orderGoodsId":959,"orderId":775,"goodsId":182,"num":2,"createTime":1504247777000,"updateTime":1504247777000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}}]}],"page":{"pageNo":1,"pageSize":10,"totalCount":32,"totalPage":4}}
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
         * orderList : [{"orderId":803,"orderCode":"20170902143700447","orderStatus":3,"userId":56289,"orderPrice":44.7,"orderOldPrice":44.7,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504334246000,"payType":3,"deliveryLocationId":133,"deliveryType":0,"deliveryAddress":"江苏南京浦口天润城(百润路) 2号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.734322,"latitude":32.143663,"distance":324.2,"shopId":1001,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504334221000,"updateTime":1504421986000,"orderGoodsList":[{"orderGoodsId":990,"orderId":803,"goodsId":171,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":171,"name":"B级-新沂水蜜桃","param":"[{\"key\":\"产地\",\"value\":\"徐州\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2粒(约500g)","price":9.9,"oldPrice":12.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501744916719.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501744897722.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745019000,"updateTime":1502157782000,"detail":null}},{"orderGoodsId":991,"orderId":803,"goodsId":117,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":117,"name":"A级-8424西瓜","param":"[{\"key\":\"产地\",\"value\":\"上海\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"1个(约4000g)","price":15.9,"oldPrice":19.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501740177314.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501740274507.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501740405000,"updateTime":1501740405000,"detail":null}},{"orderGoodsId":992,"orderId":803,"goodsId":179,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":179,"name":"A级-智利红提","param":"[{\"key\":\"产地\",\"value\":\"智利\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"500-550g","price":14.9,"oldPrice":19.9,"deliveryPrice":null,"tag":"","cateId":1060,"image":"http://or6975drq.bkt.clouddn.com/1501745881764.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745868296.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745983000,"updateTime":1501745983000,"detail":null}}]},{"orderId":798,"orderCode":"20170901174050435","orderStatus":3,"userId":56289,"orderPrice":43.6,"orderOldPrice":43.6,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504258865000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504258850000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":982,"orderId":798,"goodsId":178,"num":4,"createTime":1504258850000,"updateTime":1504258850000,"goods":{"goodsId":178,"name":"A级-新西兰加力果","param":"[{\"key\":\"产地\",\"value\":\"新西兰\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2个(约400g)","price":9.9,"oldPrice":13.8,"deliveryPrice":null,"tag":"","cateId":1062,"image":"http://or6975drq.bkt.clouddn.com/1501745694999.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745778572.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745892000,"updateTime":1501745892000,"detail":null}}]},{"orderId":797,"orderCode":"20170901173627365","orderStatus":3,"userId":56289,"orderPrice":23.9,"orderOldPrice":23.9,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504258601000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504258588000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":981,"orderId":797,"goodsId":182,"num":1,"createTime":1504258588000,"updateTime":1504258588000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}}]},{"orderId":796,"orderCode":"20170901173217790","orderStatus":3,"userId":56289,"orderPrice":33.7,"orderOldPrice":33.7,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504258352000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504258338000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":980,"orderId":796,"goodsId":178,"num":3,"createTime":1504258338000,"updateTime":1504258338000,"goods":{"goodsId":178,"name":"A级-新西兰加力果","param":"[{\"key\":\"产地\",\"value\":\"新西兰\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2个(约400g)","price":9.9,"oldPrice":13.8,"deliveryPrice":null,"tag":"","cateId":1062,"image":"http://or6975drq.bkt.clouddn.com/1501745694999.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745778572.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745892000,"updateTime":1501745892000,"detail":null}}]},{"orderId":795,"orderCode":"20170901172757209","orderStatus":3,"userId":56289,"orderPrice":19.9,"orderOldPrice":19.9,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504258091000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504258078000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":979,"orderId":795,"goodsId":117,"num":1,"createTime":1504258078000,"updateTime":1504258078000,"goods":{"goodsId":117,"name":"A级-8424西瓜","param":"[{\"key\":\"产地\",\"value\":\"上海\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"1个(约4000g)","price":15.9,"oldPrice":19.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501740177314.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501740274507.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501740405000,"updateTime":1501740405000,"detail":null}}]},{"orderId":794,"orderCode":"20170901172058317","orderStatus":3,"userId":56289,"orderPrice":33.7,"orderOldPrice":33.7,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504257963000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504257658000,"updateTime":1504346386000,"orderGoodsList":[{"orderGoodsId":978,"orderId":794,"goodsId":128,"num":1,"createTime":1504257658000,"updateTime":1504257658000,"goods":{"goodsId":128,"name":"德博黑啤酒","param":"[{\"key\":\"产地\",\"value\":\"德国\"},{\"key\":\"包装\",\"value\":\"听\"}]","weight":null,"spec":"3听（500ml*3）","price":29.7,"oldPrice":35,"deliveryPrice":null,"tag":"","cateId":1068,"image":"http://or6975drq.bkt.clouddn.com/1501740896610.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501741980707.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501742138000,"updateTime":1501747370000,"detail":null}}]},{"orderId":779,"orderCode":"20170901145230651","orderStatus":3,"userId":56289,"orderPrice":31.6,"orderOldPrice":31.6,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504248764000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504248750000,"updateTime":1504335586000,"orderGoodsList":[{"orderGoodsId":963,"orderId":779,"goodsId":180,"num":2,"createTime":1504248750000,"updateTime":1504248750000,"goods":{"goodsId":180,"name":"A级-泰国椰青","param":"[{\"key\":\"产地\",\"value\":\"泰国\"},{\"key\":\"包装\",\"value\":\"个\"}]","weight":null,"spec":"1个","price":13.8,"oldPrice":19.9,"deliveryPrice":null,"tag":"","cateId":3,"image":"http://or6975drq.bkt.clouddn.com/1501745890154.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745958357.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746068000,"updateTime":1502156523000,"detail":null}}]},{"orderId":778,"orderCode":"20170901144544485","orderStatus":3,"userId":56289,"orderPrice":23.8,"orderOldPrice":23.8,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504248358000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504248345000,"updateTime":1504335586000,"orderGoodsList":[{"orderGoodsId":962,"orderId":778,"goodsId":171,"num":2,"createTime":1504248345000,"updateTime":1504248345000,"goods":{"goodsId":171,"name":"B级-新沂水蜜桃","param":"[{\"key\":\"产地\",\"value\":\"徐州\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2粒(约500g)","price":9.9,"oldPrice":12.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501744916719.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501744897722.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745019000,"updateTime":1502157782000,"detail":null}}]},{"orderId":777,"orderCode":"20170901144100015","orderStatus":3,"userId":56289,"orderPrice":43.8,"orderOldPrice":43.8,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504248071000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504248061000,"updateTime":1504335586000,"orderGoodsList":[{"orderGoodsId":961,"orderId":777,"goodsId":182,"num":2,"createTime":1504248061000,"updateTime":1504248061000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}}]},{"orderId":775,"orderCode":"20170901143617193","orderStatus":3,"userId":56289,"orderPrice":43.8,"orderOldPrice":43.8,"expressPrice":4,"couponReceiveId":null,"couponPrice":0,"payTime":1504247790000,"payType":3,"deliveryLocationId":129,"deliveryType":0,"deliveryAddress":"江苏南京浦口秦冯路 57号","contactMobile":"18319628135","contactPersion":"为了测试","longitude":118.717642,"latitude":32.166865,"distance":1771.5,"shopId":1023,"source":"WECHAT","delStatus":0,"deliveryTime":null,"remarke":"","createTime":1504247777000,"updateTime":1504335586000,"orderGoodsList":[{"orderGoodsId":959,"orderId":775,"goodsId":182,"num":2,"createTime":1504247777000,"updateTime":1504247777000,"goods":{"goodsId":182,"name":"B级-冬枣","param":"[{\"key\":\"产地\",\"value\":\"山东\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"约500g","price":19.9,"oldPrice":25.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501746075298.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501746139596.png","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501746339000,"updateTime":1501746339000,"detail":null}}]}]
         * page : {"pageNo":1,"pageSize":10,"totalCount":32,"totalPage":4}
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
             * totalCount : 32
             * totalPage : 4
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
             * orderId : 803
             * orderCode : 20170902143700447
             * orderStatus : 3
             * userId : 56289
             * orderPrice : 44.7
             * orderOldPrice : 44.7
             * expressPrice : 4
             * couponReceiveId : null
             * couponPrice : 0
             * payTime : 1504334246000
             * payType : 3
             * deliveryLocationId : 133
             * deliveryType : 0
             * deliveryAddress : 江苏南京浦口天润城(百润路) 2号
             * contactMobile : 18319628135
             * contactPersion : 为了测试
             * longitude : 118.734322
             * latitude : 32.143663
             * distance : 324.2
             * shopId : 1001
             * source : WECHAT
             * delStatus : 0
             * deliveryTime : null
             * remarke :
             * createTime : 1504334221000
             * updateTime : 1504421986000
             * orderGoodsList : [{"orderGoodsId":990,"orderId":803,"goodsId":171,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":171,"name":"B级-新沂水蜜桃","param":"[{\"key\":\"产地\",\"value\":\"徐州\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2粒(约500g)","price":9.9,"oldPrice":12.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501744916719.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501744897722.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745019000,"updateTime":1502157782000,"detail":null}},{"orderGoodsId":991,"orderId":803,"goodsId":117,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":117,"name":"A级-8424西瓜","param":"[{\"key\":\"产地\",\"value\":\"上海\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"1个(约4000g)","price":15.9,"oldPrice":19.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501740177314.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501740274507.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501740405000,"updateTime":1501740405000,"detail":null}},{"orderGoodsId":992,"orderId":803,"goodsId":179,"num":1,"createTime":1504334221000,"updateTime":1504334221000,"goods":{"goodsId":179,"name":"A级-智利红提","param":"[{\"key\":\"产地\",\"value\":\"智利\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"500-550g","price":14.9,"oldPrice":19.9,"deliveryPrice":null,"tag":"","cateId":1060,"image":"http://or6975drq.bkt.clouddn.com/1501745881764.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501745868296.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745983000,"updateTime":1501745983000,"detail":null}}]
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
            private long payTime;
            private int payType;
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
                 * orderGoodsId : 990
                 * orderId : 803
                 * goodsId : 171
                 * num : 1
                 * createTime : 1504334221000
                 * updateTime : 1504334221000
                 * goods : {"goodsId":171,"name":"B级-新沂水蜜桃","param":"[{\"key\":\"产地\",\"value\":\"徐州\"},{\"key\":\"包装\",\"value\":\"份\"}]","weight":null,"spec":"2粒(约500g)","price":9.9,"oldPrice":12.8,"deliveryPrice":null,"tag":"","cateId":1064,"image":"http://or6975drq.bkt.clouddn.com/1501744916719.jpg","thumImage":"http://or6975drq.bkt.clouddn.com/1501744897722.jpg","stockNum":999,"saleNum":0,"grade":null,"shopId":0,"status":1,"delStatus":0,"createTime":1501745019000,"updateTime":1502157782000,"detail":null}
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
}
