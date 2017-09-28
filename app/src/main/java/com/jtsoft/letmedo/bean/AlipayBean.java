package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/9/28.
 * 支付宝接口
 */

public class AlipayBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1506570250701
     * response : {"orderStr":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017091808799957&biz_content=%7B%22out_trade_no%22%3A%2220170928114400332%22%2C%22passback_params%22%3A%22%257Bsubject%253D%25E8%25AE%25A9%25E6%2588%2591%25E6%259D%25A5%25E6%259E%259C%25E6%25BA%2590%25E8%25AE%25A2%25E5%258D%2595%253A20170928114400332%252C%2Breturn_url%253Dhttp%253A%252F%252Ftest.rangwolai.com.cn%252Fweixin%252Freturn_url.htm%252C%2Bnotify_url%253Dhttp%253A%252F%252Ftest.rangwolai.com.cn%252Fweixin%252FaliPayNotify%252FPAY_ORDER.do%252C%2Bout_trade_no%253D20170928114400332%252C%2Btotal_amount%253D100.00%257D%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%AE%A9%E6%88%91%E6%9D%A5%E6%9E%9C%E6%BA%90%E8%AE%A2%E5%8D%95%3A20170928114400332%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22100.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.wap.pay&notify_url=http%3A%2F%2Ftest.rangwolai.com.cn%2Fweixin%2FaliPayNotify%2FBUSINESS_TYPE.do&sign=iCK4VKalns2fxHruLldUd5PNNKrHLDepoeTuKs8EJCZwgNgjWwGzf6xWStDv0HWKRcY2qWdYMPrhvuYVxo%2FV3PiYT75YjW0u0Rc4xCRQ2ott4Tf2amrMW%2FZou95vbkmZO9RvUAXf8HGzqiBYRIXIMfimhy9DPOJA3KZNuWwTKn4yLs0f3Zz7DhEt8yyO7CzmiIEjV1QuNQT8mMbLvSM%2FgXFDBauv2SPUYSOFF41dIxT8blMhB3PRvlMgTt%2BPBGQg7zz71EsEOol2PCdjyqmtwBnTg7f%2Bv1Z4gwQl9mKNk3T53dUwBmLDpBUXx%2FiseaURc1qFy76bZ%2FaEEdSvnsyM1A%3D%3D&sign_type=RSA2&timestamp=2017-09-28+11%3A44%3A10&version=1.0"}
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
         * orderStr : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017091808799957&biz_content=%7B%22out_trade_no%22%3A%2220170928114400332%22%2C%22passback_params%22%3A%22%257Bsubject%253D%25E8%25AE%25A9%25E6%2588%2591%25E6%259D%25A5%25E6%259E%259C%25E6%25BA%2590%25E8%25AE%25A2%25E5%258D%2595%253A20170928114400332%252C%2Breturn_url%253Dhttp%253A%252F%252Ftest.rangwolai.com.cn%252Fweixin%252Freturn_url.htm%252C%2Bnotify_url%253Dhttp%253A%252F%252Ftest.rangwolai.com.cn%252Fweixin%252FaliPayNotify%252FPAY_ORDER.do%252C%2Bout_trade_no%253D20170928114400332%252C%2Btotal_amount%253D100.00%257D%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%AE%A9%E6%88%91%E6%9D%A5%E6%9E%9C%E6%BA%90%E8%AE%A2%E5%8D%95%3A20170928114400332%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22100.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.wap.pay&notify_url=http%3A%2F%2Ftest.rangwolai.com.cn%2Fweixin%2FaliPayNotify%2FBUSINESS_TYPE.do&sign=iCK4VKalns2fxHruLldUd5PNNKrHLDepoeTuKs8EJCZwgNgjWwGzf6xWStDv0HWKRcY2qWdYMPrhvuYVxo%2FV3PiYT75YjW0u0Rc4xCRQ2ott4Tf2amrMW%2FZou95vbkmZO9RvUAXf8HGzqiBYRIXIMfimhy9DPOJA3KZNuWwTKn4yLs0f3Zz7DhEt8yyO7CzmiIEjV1QuNQT8mMbLvSM%2FgXFDBauv2SPUYSOFF41dIxT8blMhB3PRvlMgTt%2BPBGQg7zz71EsEOol2PCdjyqmtwBnTg7f%2Bv1Z4gwQl9mKNk3T53dUwBmLDpBUXx%2FiseaURc1qFy76bZ%2FaEEdSvnsyM1A%3D%3D&sign_type=RSA2&timestamp=2017-09-28+11%3A44%3A10&version=1.0
         */

        private String orderStr;

        public String getOrderStr() {
            return orderStr;
        }

        public void setOrderStr(String orderStr) {
            this.orderStr = orderStr;
        }
    }
}
