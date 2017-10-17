package com.jtsoft.letmedo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jtsoft.letmedo.common.pay.weixin.WXPay;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "WXPayEntryActivity";

	/*public static final int PAY_FROM_TYPE_UN_SPECIFIC = -1;
	public static final int PAY_FROM_TYPE_COUNTER = 0;
	public static final int PAY_FROM_TYPE_PAY_MANAGER = 1;

	private static int payFromType = PAY_FROM_TYPE_UN_SPECIFIC;*/
	private static String paySerial;
	
    private IWXAPI api;
    
    private int error_code = 10000;
    
    private TextView tv;

	private static String appId;

	public static void setAppId(String id) {
		appId = id;
	}

	/*public static int getPayFromType() {
		return payFromType;
	}

	public static void setPayFromType(int type) {
		payFromType = type;
	}*/

	public static String getPaySerial() {
		return paySerial;
	}

	public static void setPaySerial(String serial) {
		paySerial = serial;
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.pay_result);
        //tv = (TextView) findViewById(R.id.tv_wxpay_result);
		//setContentView(R.layout.progress_circol);
		View view = new View(this);
		view.setBackgroundColor(0x00ffffff);
		view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT));
		setContentView(view);
    	//api = WXAPIFactory.createWXAPI(this, ConfigAPP.WEIXIN_PAY_APP_ID);
		api = WXAPIFactory.createWXAPI(this, appId);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
		WXPay.getInstance().onResp(0);
//		Toast.makeText(this, "onPayFinish, errCode = " + resp.errCode,
//				Toast.LENGTH_SHORT).show();
		/*if(getPayFromType() == PAY_FROM_TYPE_COUNTER){
			WXPay.getInstance(this).onResp(0);
			setPayFromType(PAY_FROM_TYPE_UN_SPECIFIC);
		}*/
		/*else if(getPayFromType() == PAY_FROM_TYPE_PAY_MANAGER){
			PayManager.getInstance(this).onPayResult(PayManager.PAY_METHOD_WEIXIN,new JSONObject(resp).toString());
		}else {
			if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
				this.error_code = resp.errCode;
				if (error_code == 0) {
					//tv.setText("支付结果：成功");
					//客户端得到支付结果后，向服务器端提交
					PayHelper.getInstance().weixinpayEnd(true, error_code + "");
				} else if (error_code == -2) {
					//tv.setText("支付结果：取消操作");
					//ToastHelper.showToastShort(this, getString(R.string.pay_weixin_cancel));
					ToastUtils.show(this,getString(R.string.pay_weixin_cancel));
					PayHelper.getInstance().weixinpayEnd(false, error_code + "");
				} else {
					//tv.setText("支付结果：失败");
					//ToastHelper.showToastShort(this, getString(R.string.pay_weixin_fail));
					ToastUtils.show(this,getString(R.string.pay_weixin_fail));
					//客户端得到支付结果后，向服务器端提交
					PayHelper.getInstance().weixinpayEnd(false, error_code + "");
				}
			}
		}*/
		finish();
	}

//	public void onSubmit(View v){
//		finish();
//	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		Intent intent  = new Intent();
		if(error_code == 0){

		}else if(error_code == -1){

		}else{

		}
		sendBroadcast(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("WXPayEntryActivity"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("WXPayEntryActivity"); // 保证 onPageEnd 在onPause
		// 之前调用,因为 onPause 中会保存信息
		MobclickAgent.onPause(this);
	}
}