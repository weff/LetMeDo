package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jtsoft.letmedo.MainActivity;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.CommonAdressAdapter;
import com.jtsoft.letmedo.bean.GetAddressBean;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/26.
 * 地址管理页面
 */

public class CommonAdressActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private RelativeLayout Relative;
    private ListView ListView;
    private Intent intent;
    private Button addAddress;
    private CommonAdressAdapter adapter;
    public String strToken;
    private List<GetAddressBean.ResponseBean.OrderAddressListBean> orderAddressList;
    private int goodsaddress;
    private double latitude;
    private double longitude;
    private ImageView Img;
    private TextView Scrb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonadress);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //控件初始化
        initView();
        initRespone();

    }

    private void initRespone() {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listOrderAddress.do?token=" + strToken)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(CommonAdressActivity.this, R.string.no_net + "");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                GetAddressBean getAddressBean = gson.fromJson(strJson, GetAddressBean.class);
                if (getAddressBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    orderAddressList = getAddressBean.getResponse().getOrderAddressList();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //数据初始化
                            initData();
                        }
                    });
                }
            }
        });
    }

    //从编辑页面返回回来地址管理对应条目的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NetWorkUtils.FLAG_EDITADDRESS) {
            if (resultCode == NetWorkUtils.FLAG_EDITADDRESS_OUT) {
                initRespone();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initData() {
        //如果listview里没有数据
        if (orderAddressList.size() == 0) {
            Relative.setVisibility(View.VISIBLE);
            Img.setVisibility(View.VISIBLE);
            Scrb.setVisibility(View.VISIBLE);
            ListView.setVisibility(View.INVISIBLE);
        } else {
            Relative.setVisibility(View.INVISIBLE);
            Img.setVisibility(View.INVISIBLE);
            Scrb.setVisibility(View.INVISIBLE);
            ListView.setVisibility(View.VISIBLE);
            if (adapter == null) {
                adapter = new CommonAdressAdapter(this, orderAddressList);
                ListView.setAdapter(adapter);
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        //返回按键控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        Edit.setVisibility(View.INVISIBLE);
        //地址为空的布局控件
        Relative = (RelativeLayout) findViewById(R.id.relative);
        //地址为空的图片控件
        Img = (ImageView) findViewById(R.id.image);
        //地址为空的文字
        Scrb = (TextView) findViewById(R.id.scrb);
        //增加地址控件
        addAddress = (Button) findViewById(R.id.addAdress);
        addAddress.setOnClickListener(this);
        //地址显示控件
        ListView = (ListView) findViewById(R.id.list_adress);
        ListView.setOnItemClickListener(this);
        Back.setOnClickListener(this);
        Tittle.setText(R.string.manageaddress);
        strToken = SharedpreferencesManager.getToken();
        //从确认订单传来的值
        intent = getIntent();
        goodsaddress = intent.getIntExtra("goodsaddress", -1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAdress:
                intent = new Intent(CommonAdressActivity.this, AddCommonAdressActivity.class);
                startActivityForResult(intent, NetWorkUtils.FLAG_ADDADDRESS);
                break;
            case R.id.back_press:
                intent = new Intent(CommonAdressActivity.this, MainActivity.class);
                intent.putExtra("myfragment", 4);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    //获取读取定位权限后的操作（Android6.0）
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//        }
//    }

    @Override
    public void onBackPressed() {
        intent = new Intent(CommonAdressActivity.this, MainActivity.class);
        intent.putExtra("myfragment", 4);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        latitude = orderAddressList.get(position).getLatitude();
        longitude = orderAddressList.get(position).getLongitude();

        int addressId = orderAddressList.get(position).getAddressId();
        Log.e("TAG", "addre=====>" + addressId);
        String contactName = orderAddressList.get(position).getContactName();
        Log.e("TAG", "contactName=====>" + contactName);
        String contactPhone = orderAddressList.get(position).getContactPhone();
        String detailAddress = orderAddressList.get(position).getDetailAddress();
        String provinceName = orderAddressList.get(position).getProvinceName();
        String cityName = orderAddressList.get(position).getCityName();
        String districtName = orderAddressList.get(position).getDistrictName();
        int provinceId = orderAddressList.get(position).getProvinceId();
        int cityId = orderAddressList.get(position).getCityId();
        int districtId = orderAddressList.get(position).getDistrictId();
        if (goodsaddress == NetWorkUtils.SHOPSTORE_ADDRESS) {
            intent = new Intent(CommonAdressActivity.this, MyBillActivity.class);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            Log.e("LLL", latitude + ":::" + longitude);
            intent.putExtra("contentname", contactName);
            intent.putExtra("contentphone", contactPhone);
            intent.putExtra("contentaddress", detailAddress);
            intent.putExtra("provinceName", provinceName);
            intent.putExtra("cityName", cityName);
            intent.putExtra("districtName", districtName);
            intent.putExtra("addressID", addressId);
            setResult(NetWorkUtils.FALG_GOODS_ADDRESS_BACK, intent);
            finish();
            goodsaddress = 11;
        } else {
            //跳转到编辑页面
            intent = new Intent(CommonAdressActivity.this, EditCommonAdressActivity.class);
            intent.putExtra("addressId", addressId);
            Log.e("TAG","addressId_common:" + addressId);
            intent.putExtra("province",provinceName);
            intent.putExtra("city",cityName);
            intent.putExtra("district",districtName);
            intent.putExtra("detailAddress",detailAddress);
            intent.putExtra("provinceId",provinceId);
            intent.putExtra("cityId",cityId);
            intent.putExtra("latitude",latitude);
            intent.putExtra("longitude",longitude);
            Log.e("TAG","latitude:" + latitude + "::longitude:" + longitude);
            intent.putExtra("districtId",districtId);
            startActivityForResult(intent, NetWorkUtils.FLAG_EDIT_DISTRICT);
        }
    }


}
