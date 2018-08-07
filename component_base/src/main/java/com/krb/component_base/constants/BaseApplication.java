package com.krb.component_base.constants;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.krb.component_base.BuildConfig;
import com.krb.component_base.arouter.RouterConfig;

/**
 * Created by yinbing on 2018/7/24 0024.
 */

public class BaseApplication extends Application {
    public static boolean IS_DEBUG = BuildConfig.DEBUG ;
    private static BaseApplication mBaseApplication ;
    //Activity管理
    private ActivityControl mActivityControl;

    public ActivityControl getActivityControl() {
        return mActivityControl;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mBaseApplication = this;
    }

    public static BaseApplication getAppContext(){
        return mBaseApplication;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityControl = new ActivityControl();
        //arouter路由初始化
        RouterConfig.init(this,BuildConfig.DEBUG);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        exitApp();
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        mActivityControl.finishiAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
