package com.jtsoft.letmedo.webinterface;

import android.webkit.JavascriptInterface;

/**
 * Created by admin on 2017/9/28.
 * 与js交互的接口
 */

public class WebInterface {

    //跳到商品详情
    @JavascriptInterface
    public void oGoodsDetail(int goodsId) {
    }
    //加入到购物车
    @JavascriptInterface
    public void toShopCarts(int goodsId) {

    }
}
