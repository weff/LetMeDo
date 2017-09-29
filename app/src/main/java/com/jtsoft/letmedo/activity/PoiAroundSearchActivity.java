package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.amap.api.maps2d.MapView;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.apiKey;
import static com.amap.api.maps2d.MapsInitializer.setApiKey;

public class PoiAroundSearchActivity extends FragmentActivity implements TextWatcher, Inputtips.InputtipsListener {
    private MapView mapview;
    private String strToken;
    private AutoCompleteTextView searchText;
    private ListView minputlist;
    private String city;
    private List<HashMap<String, String>> listString;
    private Intent intent;
    private int addcommon;
    private int editcommon;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setApiKey(apiKey + "");
        setContentView(R.layout.activity_mapsearch);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initView();
        String s = SplashActivity.sHA1(this);
        Log.e("TAG", "s....." + s);

    }

    private void initView() {
        //地图控件
        mapview = (MapView) findViewById(R.id.mapView);
        strToken = SharedpreferencesManager.getToken();
        //搜索控件
        searchText = (AutoCompleteTextView) findViewById(R.id.input_edittext);
        searchText.addTextChangedListener(this);

        //ListView控件
        minputlist = (ListView) findViewById(R.id.listview);
        init();
    }


    /**
     * 初始化AMap对象
     */
    private void init() {
        //从添加地址返回过来的数据
        intent = getIntent();
        //添加地址页面从过来的数据
        city = intent.getStringExtra("city");
        Log.e("TAG", "city====" + city);
        addcommon = intent.getIntExtra("addcommon", -1);
        //编辑地址页面传过来的数据
        editcommon = intent.getIntExtra("editcommon", -1);

    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapview.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapview.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }

    //TextWatcher实现方法
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String nextText = s.toString().trim();
        Log.e("TAG", "nextText====" + nextText);
        InputtipsQuery inputquery = new InputtipsQuery(nextText, city);
        inputquery.setCityLimit(true);
        Inputtips inputtips = new Inputtips(PoiAroundSearchActivity.this, inputquery);
        inputtips.setInputtipsListener(this);
        inputtips.requestInputtipsAsyn();
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    //InputtipsListener实现方法
    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        listString = new ArrayList<>();
        Log.e("TAG","list==" + list);
        for (int j = 0; j < list.size(); j++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", list.get(j).getName());
            map.put("address", list.get(j).getDistrict());
            listString.add(map);
        }
        adapter = new SimpleAdapter(getApplicationContext(), listString, R.layout.item_layout, new String[]{"name", "address"}, new int[]{R.id.poi_field_id, R.id.poi_value_id});
        Log.e("TAG","listString----" + listString);
        minputlist.setAdapter(adapter);
        //listview设置条目点击事件
        initListView();
    }

    private void initListView() {
        minputlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchText.setText(listString.get(position).get("name"));
                if (addcommon == NetWorkUtils.ADD) {
                    intent = new Intent(PoiAroundSearchActivity.this, AddCommonAdressActivity.class);
                    intent.putExtra("name", listString.get(position).get("name"));
                    minputlist.setVisibility(View.INVISIBLE);
                    setResult(NetWorkUtils.FLAG_ADD_DISTRICT_result, intent);
                    finish();

                } else if (editcommon == NetWorkUtils.EDIT) {
                    intent = new Intent(PoiAroundSearchActivity.this, EditCommonAdressActivity.class);
                    intent.putExtra("name", listString.get(position).get("name"));
                    setResult(NetWorkUtils.FLAG_EDIT_DISTRICT_result, intent);
                    finish();
                }
            }
        });
        adapter.notifyDataSetChanged();
    }
}
