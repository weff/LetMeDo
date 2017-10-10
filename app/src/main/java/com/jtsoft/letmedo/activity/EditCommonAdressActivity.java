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
import com.jtsoft.letmedo.bean.CommonAdressBean;
import com.jtsoft.letmedo.bean.DeleteAddressBean;
import com.jtsoft.letmedo.bean.GetAddressFromCommonAddressBean;
import com.jtsoft.letmedo.bean.SaveEditAddressBean;
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
 * 编辑地址页面
 */

public class EditCommonAdressActivity extends AppCompatActivity implements View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener {

    private ImageView Back;
    private TextView Tittle;
    private ImageView delete;
    private EditText etName;
    private EditText etPhone;
    private LinearLayout Province;
    private LinearLayout City;
    private LinearLayout District;
    private TextView tvAddress;
    private Button SaveButton;
    private TextView tvProvince;
    private TextView tvCity;
    private TextView tvDistrict;
    private Intent intent;
    private String strToken;
    private Gson gson;
    private String strJson;
    private int provinceId;
    private int cityId;
    private int districtId;
    private double longitude;
    private Request request;
    private OkHttpClient client;
    private int addressId;
    private List<CommonAdressBean.ResponseBean.ProvincesBean> provinces;
    private List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean> cities;
    private List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean.DistrictsBean> districts;
    private PopupWindow popupWindow;
    private GeocodeSearch geocodeSearch;
    private double latitude;
    private CommonAdressBean.ResponseBean.ProvincesBean provincesBean;
    private CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean citiesBean;
    private List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean> citiesLists;
    private List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean.DistrictsBean> districtsLists;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
    private int commonProId;
    private int commonCityId;
    private int commonDisId;
    private double etLatitude;
    private double etLongitude;
    private EditText HouseNum;
    private String mAddres;
    private String mHouseNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcommonadress);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        //控件初始化
        initView();
    }

    private void initData() {
        //初始化地址接口
        initAddressList();
        //从地址列表传过来的值
        intent = getIntent();
        addressId = intent.getIntExtra("addressId", -1);
        Log.e("TAG", "addressId===" + addressId);
        province = intent.getStringExtra("province");
        city = intent.getStringExtra("city");
        district = intent.getStringExtra("district");
        etLatitude = intent.getDoubleExtra("latitude", -1);
        etLongitude = intent.getDoubleExtra("longitude", -1);
        detailAddress = intent.getStringExtra("detailAddress");
        commonProId = intent.getIntExtra("provinceId", -1);
        commonCityId = intent.getIntExtra("cityId", -1);
        commonDisId = intent.getIntExtra("districtId", -1);
        //从地址列表返回到编辑地址的接口
        initGetAddress(addressId);
        Back.setOnClickListener(this);
        Tittle.setText(R.string.modifyaddress);
        delete.setOnClickListener(this);
        Province.setOnClickListener(this);
        City.setOnClickListener(this);
        District.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        SaveButton.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NetWorkUtils.FLAG_EDIT_DISTRICT) {
            if (resultCode == NetWorkUtils.FLAG_EDIT_DISTRICT_result) {
                String address = data.getStringExtra("name");
                tvAddress.setText(address);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initAddressList() {
        request = new Request.Builder()
                .url(Constant.CONSTANT + "/listProvince.do")
                .build();
        client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(EditCommonAdressActivity.this, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                gson = new Gson();
                final CommonAdressBean commonAdressBean = gson.fromJson(strJson, CommonAdressBean.class);
                if (commonAdressBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    //获取省市区数据
                    provinces = commonAdressBean.getResponse().getProvinces();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(EditCommonAdressActivity.this, (String) commonAdressBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initGetAddress(int addressId) {
        request = new Request.Builder()
                .url(Constant.CONSTANT + "/getOrderAddress.do?token=" + strToken + "&addressId=" + addressId)
                .build();
        client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(EditCommonAdressActivity.this, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                strJson = response.body().string();
                gson = new Gson();
                final GetAddressFromCommonAddressBean getAddressFromCommonAddressBean = gson.fromJson(strJson, GetAddressFromCommonAddressBean.class);
                if (getAddressFromCommonAddressBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    final GetAddressFromCommonAddressBean.ResponseBean.OrderAddressBean orderAddress = getAddressFromCommonAddressBean.getResponse().getOrderAddress();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //从地址列表返回到编辑地址的数据展示
                            String contactName = orderAddress.getContactName();
                            String contactPhone = orderAddress.getContactPhone();
                            String cityName = orderAddress.getCityName();
                            String districtName = orderAddress.getDistrictName();
                            String provinceName = orderAddress.getProvinceName();
                            String detailAddress = orderAddress.getDetailAddress();
                            String[] split = detailAddress.split(" ");
                            //详细地址
                            mAddres = split[0];
                            //门牌号
                            mHouseNum = split[1];
                            HouseNum.setText(mHouseNum);
                            etName.setText(contactName);
                            etPhone.setText(contactPhone);
                            tvProvince.setText(provinceName);
                            tvCity.setText(cityName);
                            tvDistrict.setText(districtName);
                            tvAddress.setText(mAddres);

                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(EditCommonAdressActivity.this, (String) getAddressFromCommonAddressBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initView() {

        strToken = SharedpreferencesManager.getToken();
        //返回按键控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题
        Tittle = (TextView) findViewById(R.id.title_name);
        //删除控件
        delete = (ImageView) findViewById(R.id.delete);
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
        HouseNum = (EditText) findViewById(R.id.house_number);
        //保存控件
        SaveButton = (Button) findViewById(R.id.saveAdress);

        //数据初始化
        initData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            case R.id.delete:
                //删除逻辑(先关闭当前页面，再删除地址管理对应的条目地址)
                initDelete(addressId, 1);
                break;
            case R.id.province:
                //点击省区控件，展示省份
                showProvincePopupWindow();
                tvCity.setText("选择城市");
                tvDistrict.setText("选择地区");
                tvAddress.setText("点击选择");
                break;
            case R.id.city:
                //点击市区控件，展示市区
                if (tvProvince.getText().toString().trim().equals("选择省份")) {
                    ToastUtil.showShort(this, "请先选择省份");
                    return;
                } else {
                    for (int i = 0; i < provinces.size(); i++) {
                        if (tvProvince.getText().toString().equals(provinces.get(i).getProvName())) {
                            citiesLists = provinces.get(i).getCities();
                            showCityPopupWindow();
                        }
                        break;
                    }
                    tvDistrict.setText("选择地区");
                    tvAddress.setText("点击选择");

                }
                break;
            case R.id.district:
                //点击地区控件，展示地区
                if (tvCity.getText().toString().trim().equals("选择市区")) {
                    ToastUtil.showShort(this, "请先选择市区");
                    return;
                } else {
                    for (int i = 0; i < provinces.size(); i++) {
                        if (tvProvince.getText().toString().equals(provinces.get(i).getProvName())) {
                            citiesLists = provinces.get(i).getCities();
                            for (int j = 0; j < citiesLists.size(); j++) {
                                if (tvCity.getText().toString().equals(citiesLists.get(j).getCityName())) {
                                    districtsLists = citiesLists.get(j).getDistricts();
                                    showDistrictPopupWindow();
                                }
                            }
                        }
                        break;
                    }
                    tvAddress.setText("点击选择");
                }
                break;
            case R.id.address:
                //点击选择详细地址，展示地区列表
                if (tvDistrict.getText().toString().trim().equals("选择地区")) {
                    ToastUtil.showShort(this, "请先选择地区");
                    return;
                }
                intent = new Intent(EditCommonAdressActivity.this, PoiAroundSearchActivity.class);
                intent.putExtra("city", tvCity.getText().toString());
                intent.putExtra("editcommon", NetWorkUtils.EDIT);
                startActivityForResult(intent, NetWorkUtils.FLAG_EDIT_DISTRICT);
                break;
            case R.id.saveAdress:
                //判断姓名、电话以及地址是否为空
                if (etName.getText().toString().equals("") || etName.getText().toString().equals("请输入姓名")) {
                    Toast.makeText(this, R.string.notnulluser, Toast.LENGTH_SHORT).show();
                    return;
                } else if (etPhone.getText().toString().equals("") || etPhone.getText().toString().equals("请输入电话号码")) {
                    Toast.makeText(this, R.string.supplytelephone, Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvProvince.getText().toString().equals("") || tvProvince.getText().toString().equals("选择省份")) {
                    Toast.makeText(this, R.string.selectprovince, Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvCity.getText().toString().equals("") || tvCity.getText().toString().equals("选择城市")) {
                    Toast.makeText(this, R.string.selectcity, Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvDistrict.getText().toString().equals("") || tvDistrict.getText().toString().equals("选择地区")) {
                    Toast.makeText(this, R.string.selectdistrict, Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvAddress.getText().toString().equals("") || tvAddress.getText().toString().equals("点击选择")) {
                    Toast.makeText(this, R.string.supplyaddress, Toast.LENGTH_SHORT).show();
                    return;
                }else if (HouseNum.getText().toString().equals("") && HouseNum.getText().toString().equals("请输入门牌号")) {
                    ToastUtil.showShort(this,"请补充完没门牌号");
                    return;
                }
                //如果地址没有更改
                if (tvProvince.getText().toString().equals(province) && tvCity.getText().toString().equals(city) && tvDistrict.getText().toString().equals(district) &&
                        tvAddress.getText().toString().equals(mAddres) && HouseNum.getText().toString().equals(mHouseNum)) {
                    inspone(strToken, addressId, commonProId, commonCityId, commonDisId, detailAddress, etLongitude, etLatitude, etName.getText().toString(), etPhone.getText().toString());
                    Log.e("TAG", "latitude:" + etLatitude + "::longitude:" + etLongitude);
                } else {
                    geocodeSearch.setOnGeocodeSearchListener(this);
                    getLatlon(tvProvince.getText().toString() + tvCity.getText().toString() + tvDistrict.getText().toString() + tvAddress.getText().toString());
//                    //地址更改
//                    if (!tvProvince.getText().toString().equals(province) && !tvCity.getText().toString().equals(city) && !tvDistrict.getText().toString().equals(district)) {
//                        inspone(strToken, addressId, provinceId, cityId, districtId, tvAddress.getText().toString(),longitude , latitude, etName.getText().toString(), etPhone.getText().toString());
//                        Log.e("TAG", "etlatitude1:" + latitude + "::etlongitude1:" + longitude);
//                        Log.e("TAG", "addressId1:" + addressId + ":provinceId1" + provinceId + ":cityId1" + cityId + ":districtId1" + districtId);
//                    } else if (tvProvince.getText().toString().equals(province) && !tvCity.getText().toString().equals(city) && !tvDistrict.getText().toString().equals(district)) {
//                        inspone(strToken, addressId, commonProId, cityId, districtId, tvAddress.getText().toString(), longitude, latitude, etName.getText().toString(), etPhone.getText().toString());
//                        Log.e("TAG", "etlatitude2:" + latitude + "::etlongitude2:" + longitude);
//                        Log.e("TAG", "addressId2:" + addressId + ":provinceId2" + commonProId + ":cityId2" + cityId + ":districtId2" + districtId);
//                    } else if (tvProvince.getText().toString().equals(province) && tvCity.getText().toString().equals(city) && !tvDistrict.getText().toString().equals(district)) {
//                        inspone(strToken, addressId, commonProId, commonCityId, districtId, tvAddress.getText().toString(), longitude, latitude, etName.getText().toString(), etPhone.getText().toString());
//                        Log.e("TAG", "etlatitude3:" + latitude + "::etlongitude3:" + longitude);
//                        Log.e("TAG", "addressId3:" + addressId + ":provinceId3:" + commonProId + ":cityId3:" + commonCityId + ":districtId3:" + districtId);
//                    } else if (tvProvince.getText().toString().equals(province) && tvCity.getText().toString().equals(city) && tvDistrict.getText().toString().equals(district) && !tvAddress.getText().toString().equals(detailAddress)) {
//                        inspone(strToken, addressId, commonProId, commonCityId, commonDisId, tvAddress.getText().toString(), longitude, latitude, etName.getText().toString(), etPhone.getText().toString());
//                        Log.e("TAG", "etlatitude4:" + latitude + "::etlongitude4:" + longitude);
//                        Log.e("TAG", "addressId4:" + addressId + ":provinceId4:" + commonProId + ":cityId4:" + commonCityId + ":districtId4:" + commonDisId + "tvAddress4:" + tvProvince.getText().toString() + tvCity.getText().toString() + tvDistrict.getText().toString() + tvAddress.getText().toString());
//                    }

                }

                break;
            default:
                break;
        }
    }

    private void inspone(String strToken, int addressId, int commonProId, int commonCityId, int commonDisId, String detailAddress, double etLongitude, double etLatitude, String s, String s1) {
        request = new Request.Builder()
                .url(Constant.CONSTANT + "/updateOrderAddress.do?token=" + strToken + "&addressId=" + addressId + "&provinceId=" + commonProId
                        + "&cityId=" + commonCityId + "&districtId=" + commonDisId + "&detailAddress=" + detailAddress + "&longitude=" + etLongitude + "&latitude="
                        + etLatitude + "&contactName=" + s + "&contactPhone=" + s1)
                .build();
        client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(EditCommonAdressActivity.this, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                gson = new Gson();
                final SaveEditAddressBean saveEditAddressBean = gson.fromJson(strJson, SaveEditAddressBean.class);

                if (saveEditAddressBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    intent = new Intent(EditCommonAdressActivity.this, CommonAdressActivity.class);
                    startActivity(intent);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(EditCommonAdressActivity.this, (String) saveEditAddressBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }


    private void getLatlon(String address) {
        GeocodeQuery query = new GeocodeQuery(address, "");// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
        geocodeSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
    }

    //地区列表展示
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
        final DistrictAdapter adapter = new DistrictAdapter(this, districtsLists);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvDistrict.setText(districtsLists.get(position).getDistrictName());
                districtId = districtsLists.get(position).getDistrictId();
                popupWindow.dismiss();
            }
        });
        adapter.notifyDataSetChanged();
    }

    //市列表展示
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
        final CityAdapter adapter = new CityAdapter(this, citiesLists);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvCity.setText(citiesLists.get(position).getCityName());
                districts = citiesLists.get(position).getDistricts();
                cityId = citiesLists.get(position).getCityId();
                popupWindow.dismiss();
            }
        });
        adapter.notifyDataSetChanged();
    }

    //省份列表展示
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
        final ProvinceAdapter adapter = new ProvinceAdapter(this, provinces);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvProvince.setText(provinces.get(position).getProvName());
                cities = provinces.get(position).getCities();
                provinceId = provinces.get(position).getProvId();
                Log.e("TAG", "provinceId:" + provinceId);
                popupWindow.dismiss();
            }
        });
        adapter.notifyDataSetChanged();

    }

    //删除地址接口
    private void initDelete(int addressId, int i) {
        request = new Request.Builder()
                .url(Constant.CONSTANT + "/updateOrderAddress.do?token=" + strToken + "&addressId=" + addressId + "&delStatus=" + i)
                .build();
        client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(EditCommonAdressActivity.this, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                gson = new Gson();
                final DeleteAddressBean deleteAddressBean = gson.fromJson(strJson, DeleteAddressBean.class);
                if (deleteAddressBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(EditCommonAdressActivity.this, "删除成功");
                            intent = new Intent(EditCommonAdressActivity.this, CommonAdressActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(EditCommonAdressActivity.this, (String) deleteAddressBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (geocodeResult != null) {
            GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);
            latitude = address.getLatLonPoint().getLatitude();
            longitude = address.getLatLonPoint().getLongitude();
            Log.e("TAG", "etlatitude5:" + latitude + "::etlongitude5:" + longitude);

            //地址更改
            if (!tvProvince.getText().toString().equals(province) && !tvCity.getText().toString().equals(city) && !tvDistrict.getText().toString().equals(district)) {
                inspone(strToken, addressId, provinceId, cityId, districtId, tvAddress.getText().toString(),longitude , latitude, etName.getText().toString(), etPhone.getText().toString());
                Log.e("TAG", "etlatitude1:" + latitude + "::etlongitude1:" + longitude);
                Log.e("TAG", "addressId1:" + addressId + ":provinceId1" + provinceId + ":cityId1" + cityId + ":districtId1" + districtId);
            } else if (tvProvince.getText().toString().equals(province) && !tvCity.getText().toString().equals(city) && !tvDistrict.getText().toString().equals(district)) {
                inspone(strToken, addressId, commonProId, cityId, districtId, tvAddress.getText().toString(), longitude, latitude, etName.getText().toString(), etPhone.getText().toString());
                Log.e("TAG", "etlatitude2:" + latitude + "::etlongitude2:" + longitude);
                Log.e("TAG", "addressId2:" + addressId + ":provinceId2" + commonProId + ":cityId2" + cityId + ":districtId2" + districtId);
            } else if (tvProvince.getText().toString().equals(province) && tvCity.getText().toString().equals(city) && !tvDistrict.getText().toString().equals(district)) {
                inspone(strToken, addressId, commonProId, commonCityId, districtId, tvAddress.getText().toString(), longitude, latitude, etName.getText().toString(), etPhone.getText().toString());
                Log.e("TAG", "etlatitude3:" + latitude + "::etlongitude3:" + longitude);
                Log.e("TAG", "addressId3:" + addressId + ":provinceId3:" + commonProId + ":cityId3:" + commonCityId + ":districtId3:" + districtId);
            } else if (tvProvince.getText().toString().equals(province) && tvCity.getText().toString().equals(city) && tvDistrict.getText().toString().equals(district) && !tvAddress.getText().toString().equals(detailAddress)) {
                inspone(strToken, addressId, commonProId, commonCityId, commonDisId, tvAddress.getText().toString(), longitude, latitude, etName.getText().toString(), etPhone.getText().toString());
                Log.e("TAG", "etlatitude4:" + latitude + "::etlongitude4:" + longitude);
                Log.e("TAG", "addressId4:" + addressId + ":provinceId4:" + commonProId + ":cityId4:" + commonCityId + ":districtId4:" + commonDisId + "tvAddress4:" + tvProvince.getText().toString() + tvCity.getText().toString() + tvDistrict.getText().toString() + tvAddress.getText().toString());
            }

        } else {
            ToastUtil.showShort(EditCommonAdressActivity.this, "对不起，没有收到相关数据！");
            return;
        }
    }
}
