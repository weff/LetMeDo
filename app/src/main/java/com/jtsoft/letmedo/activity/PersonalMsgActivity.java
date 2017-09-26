package com.jtsoft.letmedo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.ModifyPersonalMsgBean;
import com.jtsoft.letmedo.bean.PersonalMsgBean;
import com.jtsoft.letmedo.custom.CircleImageView;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Dragon on 2017/7/17.
 * 个人信息页面
 */
public class PersonalMsgActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int CROP_PHOTO = 666;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final String IMAGE_FILE_NAME = "header_big.png";
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int DATE_DIALOG = 1;
    private static final int MMM = 11;
    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private CircleImageView ivHeader;
    private EditText etName;
    private EditText etCount;
    private TextView tvDate;
    private PopupWindow popupWindow;
    private TextView tvTakePhoto;
    private TextView tvPickPhoto;
    private TextView Cancel;
    private Intent intent;
    public static int output_X = 100;
    public static int output_Y = 100;
    private int mYear;
    private int mMonth;
    private int mDay;
    private CheckBox mFemale;
    private CheckBox mMale;
    private String userName;
    private String strToken;
    private String customerMobile;
    private String customerNickname;
    private Object customerImg;
    private int sex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalmsg);
        //控件初始化
        initView();
        initRespon();
        //数据初始化
        initData();
    }

    private void initRespon() {
      strToken = SharedpreferencesManager.getToken();
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/getCustomerInfo.do?token=" + strToken)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(PersonalMsgActivity.this,"网络请求错误");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final PersonalMsgBean personalMsgBean = gson.fromJson(strJson, PersonalMsgBean.class);
                if (personalMsgBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    customerImg = personalMsgBean.getResponse().getCustomerImg();
                    customerNickname = personalMsgBean.getResponse().getCustomerNickname();
                    customerMobile = personalMsgBean.getResponse().getCustomerMobile();
                    sex = personalMsgBean.getResponse().getCustomerSex();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ivHeader.setImageResource((Integer) customerImg);
                            etName.setText(customerNickname);
                            etCount.setText(customerMobile);
                            if (sex == 0) {
                                mMale.setChecked(true);
                                mFemale.setChecked(false);
                                sex = 0;
                            }  else if (sex == 1) {
                                mFemale.setChecked(true);
                                mMale.setChecked(false);
                                sex = 1;
                            }
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(PersonalMsgActivity.this, (String) personalMsgBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initData() {
        Back.setOnClickListener(this);
        Tittle.setText(R.string.personalmsg);
        Edit.setText(R.string.save);
        Edit.setOnClickListener(this);
        ivHeader.setOnClickListener(this);
//        tvDate.setOnClickListener(this);

    }

    private void initView() {
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        //头像控件
        ivHeader = (CircleImageView) findViewById(R.id.header);
        //昵称控件
        etName = (EditText) findViewById(R.id.name);
        //账号控件
        etCount = (EditText) findViewById(R.id.count);
        //性别选择按钮
        //女性单选按钮
        mFemale = (CheckBox) findViewById(R.id.female);
        //男性单选按钮
        mMale = (CheckBox) findViewById(R.id.male);
        //日期控件
//        tvDate = (TextView) findViewById(R.id.date);
        mMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFemale.setChecked(false);
                    sex = 0;
                }else {
                    mFemale.setChecked(true);
                    sex = 1;
                }
            }
        });

        mFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMale.setChecked(false);
                    sex = 1;
                }else {
                    mMale.setChecked(true);
                    sex = 0;
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MMM:
             if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 //拍照逻辑
                 tvPickPhoto.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                         //判断是否有SD卡
                         if (hasSdCard()) {
                             intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                             startActivityForResult(intent, CODE_CAMERA_REQUEST);
                         }
                     }
                 });

                 //从相册中选择图片的逻辑
                 tvPickPhoto.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         intent = new Intent();
                         intent.setType("image/*");
                         intent.setAction(Intent.ACTION_PICK);
                         startActivityForResult(intent, CODE_GALLERY_REQUEST);
                     }
                 });
             }else {
                 return;
             }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.back_press:
                finish();
                break;
            case R.id.edit:
                //保存用户信息  先关闭当前页面，再将数据返回给个人中心页面
                Request request = new Request.Builder()
                        .url(Constant.CONSTANT + "/updateCustomerInfo.do?token=" + strToken + "&customerImg=" + "" + "&customerNickname=" + etName.getText().toString().trim() + "&customerSex=" + sex)
                        .build();
                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShort(PersonalMsgActivity.this,"网络请求失败");
                                return;
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String strJson = response.body().string();
                        Gson gson = new Gson();
                        final ModifyPersonalMsgBean modifyPersonalMsgBean = gson.fromJson(strJson, ModifyPersonalMsgBean.class);
                        if (modifyPersonalMsgBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showShort(PersonalMsgActivity.this,"修改成功");
                                    Intent intent = new Intent(PersonalMsgActivity.this,PersonalActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showShort(PersonalMsgActivity.this, (String) modifyPersonalMsgBean.getMessage());
                                    return;
                                }
                            });
                        }
                    }
                });

                break;
            case R.id.header:
                //头像修改操作
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    //没有权限
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)) {
                        return;
                    }else {
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},MMM);
                    }
                }
                showPopupWindow();
                break;
//            case R.id.date:
//                //更改日期操作
//                showDialog(DATE_DIALOG);
//                Calendar calendar = Calendar.getInstance();
//                mYear = calendar.get(Calendar.YEAR);
//                mMonth = calendar.get(Calendar.MONTH);
//                mDay = calendar.get(Calendar.DAY_OF_MONTH);
//                break;
            default:
                break;
        }
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
//    public void display() {
//        tvDate.setText(new StringBuffer().append(mMonth + 1).append("-").append(mDay).append("-").append(mYear).append(" "));
//    }
//
//    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//            mYear = year;
//            mMonth = monthOfYear;
//            mDay = dayOfMonth;
//            display();
//        }
//    };

    //弹出一个popupwindow，进行拍照或者从相册中选择图片
    private void showPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_photo, null, false);
        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        //拍照控件
        tvTakePhoto = (TextView) view.findViewById(R.id.take_photo);
        //从相册中选择照片控件
        tvPickPhoto = (TextView) view.findViewById(R.id.pick_photo);
        //取消控件
        Cancel = (TextView) view.findViewById(R.id.cancel);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        tvTakePhoto.setOnClickListener(this);
        tvPickPhoto.setOnClickListener(this);
        //取消逻辑
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), R.string.cancel, Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;
            case CODE_CAMERA_REQUEST:
                if (hasSdCard()) {
                    File file = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(file));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setImageToHeadView(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            ivHeader.setImageBitmap(photo);
        }
    }

    //裁剪原始图片
    private void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    //判断是否有SD卡逻辑
    private boolean hasSdCard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
