package com.krb.componenttest;

import com.krb.component_base.constants.BaseApplication;

/**
 * Created by yinbing on 2018/7/24 0024.
 */

public class MyApplication extends BaseApplication {

    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this ;
    }
}
