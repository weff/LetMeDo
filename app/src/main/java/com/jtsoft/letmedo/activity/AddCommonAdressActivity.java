package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.CityAdapter;
import com.jtsoft.letmedo.adapter.DistrictAdapter;
import com.jtsoft.letmedo.adapter.ProvinceAdapter;
import com.jtsoft.letmedo.bean.AddaddressBean;
import com.jtsoft.letmedo.bean.CommonAdressBean;
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
 * 增加收货人地址页面
 */

public class AddCommonAdressActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private EditText etName;
    private EditText etPhone;
    private LinearLayout Province;
    private TextView tvProvince;
    private LinearLayout City;
    private TextView tvCity;
    private LinearLayout District;
    private TextView tvDistrict;
    private TextView tvAddress;
    private Button SaveButton;
    private PopupWindow popupWindow;
    private List<CommonAdressBean.ResponseBean.ProvincesBean> been;
    private int provId;
    private int cityId;
    private int districtId;
    private String strToken;
    private List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean> cities;
    private List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean.DistrictsBean> districts;
    private GeocodeSearch geocodeSearch;
    private double latitude;
    private double longitude;
    private Intent intent;
    private EditText etHouseNum;
    private String detailAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcommonaddress);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        geocodeSearch = new GeocodeSearch(this);
        //控件初始化
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //数据初始化
        initData();
    }

    //从所搜地址列表返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NetWorkUtils.FLAG_ADD_DISTRICT) {
            if (resultCode == NetWorkUtils.FLAG_ADD_DISTRICT_result) {
                String address = data.getStringExtra("name");
                tvAddress.setText(address);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initData() {
        initJson();
        Tittle.setText("添加地址");
        Back.setOnClickListener(this);
        Edit.setVisibility(View.INVISIBLE);
        Province.setOnClickListener(this);
        City.setOnClickListener(this);
        District.setOnClickListener(this);
        SaveButton.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
    }

    //初始化的时候，调用此接口能送货到的省市区
    private void initJson() {
        final Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listProvince.do")
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(AddCommonAdressActivity.this, R.string.no_net + "");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final CommonAdressBean commonAdressBean = gson.fromJson(strJson, CommonAdressBean.class);
                if (commonAdressBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    //省市区json
                    been = commonAdressBean.getResponse().getProvinces();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(AddCommonAdressActivity.this, (String) commonAdressBean.getMessage());
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        //返回按键控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        //输入姓名控件
        etName = (EditText) findViewById(R.id.name);
        //输入手机号控件
        etPhone = (EditText) findViewById(R.id.phonenumber);
        //省份布局控件
        Province = (LinearLayout) findViewById(R.id.province);
        //省份控件
        tvProvince = (TextView) findViewById(R.id.tvprovince);
        //城市布局控件
        City = (LinearLayout) findViewById(R.id.city);
        //城市控件
        tvCity = (TextView) findViewById(R.id.tvcity);
        //地区布局控件
        District = (LinearLayout) findViewById(R.id.district);
        //地区控件
        tvDistrict = (TextView) findViewById(R.id.tvdistrict);
        //详细地址控件
        tvAddress = (TextView) findViewById(R.id.address);
        //门牌号控件
        etHouseNum = (EditText) findViewById(R.id.house_number);
        //保存控件
        SaveButton = (Button) findViewById(R.id.saveAdress);

        strToken = SharedpreferencesManager.getToken();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            case R.id.saveAdress:
                initmap();
                break;
            case R.id.province:
                showProvincePopupWindow();
                break;
            case R.id.city:
                if (tvProvince.getText().toString().trim().equals("选择省份")) {
                    ToastUtil.showShort(this, "请先选择省份");
                    return;
                } else {
                    showCityPopupWindow();
                }

                break;
            case R.id.district:
                if (tvCity.getText().toString().trim().equals("选择市区")) {
                    ToastUtil.showShort(this, "请先选择市区");
                    return;
                } else {
                    showDistrictPopupWindow();
                }
                break;
            //详细地址控件点击
            case R.id.address:
                if (tvDistrict.getText().toString().trim().equals("选择地区")) {
                    ToastUtil.showShort(this, "请先选择地区");
                    return;
                }
                intent = new Intent(this, PoiAroundSearchActivity.class);
                intent.putExtra("city", tvCity.getText().toString());
                Log.e("TAG", "tvCity-----" + tvCity.getText().toString());
                intent.putExtra("addcommon", NetWorkUtils.ADD);
                startActivityForResult(intent, NetWorkUtils.FLAG_ADD_DISTRICT);
                break;
            default:
                break;
        }
    }

    private void initmap() {
        //详细地址的拼接
        detailAdd = tvAddress.getText().toString().trim() + " " + etHouseNum.getText().toString().trim();
        //判断姓名、电话以及地址是否为空
        if (etName.getText().toString().equals("")) {
            Toast.makeText(this, R.string.notnulluser, Toast.LENGTH_SHORT).show();
            return;
        } else if (etPhone.getText().toString().equals("")) {
            Toast.makeText(this, R.string.supplytelephone, Toast.LENGTH_SHORT).show();
            return;
        } else if (tvProvince.getText().toString().equals("")) {
            Toast.makeText(this, R.string.selectprovince, Toast.LENGTH_SHORT).show();
            return;
        } else if (tvCity.getText().toString().equals("")) {
            Toast.makeText(this, R.string.selectcity, Toast.LENGTH_SHORT).show();
            return;
        } else if (tvDistrict.getText().toString().equals("")) {
            Toast.makeText(this, R.string.selectdistrict, Toast.LENGTH_SHORT).show();
            return;
        } else if (tvAddress.getText().toString().equals("")) {
            Toast.makeText(this, R.string.supplyaddress, Toast.LENGTH_SHORT).show();
            return;
        } else if (etHouseNum.getText().toString().equals("")) {
            ToastUtil.showShort(this, "请把门牌号补充完整");
            return;
        }
        getLatlon(tvProvince.getText().toString() + tvCity.getText().toString() + tvDistrict.getText().toString() + detailAdd);
    }

    private void initSpone(String strToken, int provId, int cityId, int districtId, String s, double longitude, double latitude, String s1, String s2) {
        final Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/saveOrderAddress.do?token=" + strToken + "&provinceId=" + provId
                        + "&cityId=" + cityId + "&districtId=" + districtId + "&detailAddress=" + s + "&longitude=" + longitude
                        + "&latitude=" + latitude + "&contactName=" + s1 + "&contactPhone=" + s2)
                .build();
        Log.e("LL", "latitude:" + latitude + "longitude:" + longitude);
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(AddCommonAdressActivity.this, R.string.no_net + "");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final AddaddressBean addaddressBean = gson.fromJson(strJson, AddaddressBean.class);
                if (addaddressBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    //获取省市区联系方式，然后将数据上传服务器
                    String HouseNum = etHouseNum.getText().toString().trim();
                    if (HouseNum.equals("")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShort(AddCommonAdressActivity.this, "门牌号不能为空");
                                return;
                            }
                        });
                    } else {
                        intent = new Intent(AddCommonAdressActivity.this, CommonAdressActivity.class);
                        intent.putExtra("housenumber", etHouseNum.getText().toString().trim());
                        startActivity(intent);
                    }

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(AddCommonAdressActivity.this, (String) addaddressBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });

    }

    private void getLatlon(final String address) {
        GeocodeQuery query = new GeocodeQuery(address, "");// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
        geocodeSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                Log.e("LL", "i====" + i);
                Log.e("LL", "geocodeResult:" + geocodeResult);
                if (geocodeResult != null) {
                    GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);
                    latitude = address.getLatLonPoint().getLatitude();
                    longitude = address.getLatLonPoint().getLongitude();
                    initSpone(strToken, provId, cityId, districtId, detailAdd, longitude, latitude, etName.getText().toString(), etPhone.getText().toString());

                } else {
                    ToastUtil.showShort(AddCommonAdressActivity.this, "对不起，没有收到相关数据！");
                    return;
                }
            }
        });
    }

    //弹出省份popupwindow，设置省地址
    private void showProvincePopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_address_popupwindow, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //popup动画
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        //popup的锚点
        popupWindow.showAtLocation(Province, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setOutsideTouchable(true);  //设置点击屏幕其它地方弹出框消失
        popupWindow.showAsDropDown(view);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        final ListView listView = (ListView) view.findViewById(R.id.listview);
        final ProvinceAdapter adapter = new ProvinceAdapter(this, been);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvProvince.setText(been.get(position).getProvName());
                provId = been.get(position).getProvId();
                cities = been.get(position).getCities();
                popupWindow.dismiss();
            }
        });
        adapter.notifyDataSetChanged();
    }

    //市区popup
    private void showCityPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_address_popupwindow, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //popup动画
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        //popup的锚点
        popupWindow.showAtLocation(City, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);  //设置点击屏幕其它地方弹出框消失
        popupWindow.showAsDropDown(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        final ListView listView = (ListView) view.findViewById(R.id.listview);
        final CityAdapter adapter = new CityAdapter(this, cities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvCity.setText(cities.get(position).getCityName());
                cityId = cities.get(position).getCityId();
                districts = cities.get(position).getDistricts();
                popupWindow.dismiss();
            }
        });
        adapter.notifyDataSetChanged();
    }

    //地区popup
    private void showDistrictPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_address_popupwindow, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //popup动画
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        //popup的锚点
        popupWindow.showAtLocation(District, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);  //设置点击屏幕其它地方弹出框消失
        popupWindow.showAsDropDown(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        final ListView listView = (ListView) view.findViewById(R.id.listview);
        final DistrictAdapter adapter = new DistrictAdapter(this, districts);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvDistrict.setText(districts.get(position).getDistrictName());
                districtId = districts.get(position).getDistrictId();
                popupWindow.dismiss();
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
