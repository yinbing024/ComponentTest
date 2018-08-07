package com.krb.shop_mall.arouter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.krb.component_data.d_arouter.RouterURLS;

/**
 * Created by yinbing on 2018/8/3 0003.
 */
public class RouterCenter {
    /**
     * 测试首页
     */
    public static void toMain() {
        ARouter.getInstance().build(RouterURLS.BASE_MAIN).navigation();
    }
    /**
     * mvc 测试页面
     */
    public static void toMVCTest() {
        ARouter.getInstance().build(RouterURLS.MVC_TEST).navigation();
    }
}
