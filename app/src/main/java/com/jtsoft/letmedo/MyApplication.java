package com.jtsoft.letmedo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.githang.androidcrash.AndroidCrash;
import com.githang.androidcrash.reporter.mailreporter.CrashEmailReporter;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;

import java.io.File;

/**
 * 自定义Application
 * Created by Administrator on 2015/12/28 0028.
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "Application";
    /** 德基文件存储目录 */
    public static final String DEJI_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "Deji";

    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }

    {
        PlatformConfig.setWeixin("wx4b7f5ed656c7799a", "e1a7d6ff5575df1a27af6840c2d4c664");
        //艺+
        PlatformConfig.setSinaWeibo("2199096182", "4eab7aafd06bf46032c7015fed409e1e", "http://sns.whalecloud.com");
        
        PlatformConfig.setQQZone("1106338030", "dpj5v87j1LLonYtc");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //由于Application类本身已经单例，所以直接按以下处理即可。

        /*JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        JPushInterface.setAlias(this, //上下文对象
                "8888", //别名
                new TagAliasCallback() {//回调接口,i=0表示成功,其它设置失败
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Log.d("alias", "set alias result is" + i);
                    }
                });*/

        myApplication = this;
        //Deji.getInstance(this).init();
        Stetho.initializeWithDefaults(this);

        //程序崩溃异常处理发送邮件
        initEmailReporter();
        // 收集错误日志
        File cameraDirs = new File(DEJI_DIR);
        if (!cameraDirs.exists()) {
            cameraDirs.mkdir();
        }
        //TimeCountUtils.getInstance().getServerTime();
        registerActivityLifecycleCallbacks(this);
        Config.DEBUG = true;
        //UMShareAPI.get(this);友盟分享未实现
    }

    private void initEmailReporter() {
        CrashEmailReporter reporter = new CrashEmailReporter(this);
        reporter.setReceiver("qqianronghua@163.com");
        reporter.setSender("deji_crash2017@163.com");
        reporter.setSendPassword("deji8888");
        reporter.setSMTPHost("smtp.163.com");
        reporter.setPort("465");
        AndroidCrash.getInstance().setCrashReporter(reporter).init(this);
    }

    public int count = 0;

    @Override
    public void onActivityStopped(Activity activity) {
        Log.v("viclee", activity + "onActivityStopped");
        count--;
        if (count == 0) {
            Log.v("viclee", ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.v("viclee", activity + "onActivityStarted");
        if (count == 0) {
            Log.v("viclee", ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
        }
        count++;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.v("viclee", activity + "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.v("viclee", activity + "onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.v("viclee", activity + "onActivityPaused");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.v("viclee", activity + "onActivityDestroyed");
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.v("viclee", activity + "onActivityCreated");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
