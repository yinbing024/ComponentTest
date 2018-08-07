package com.krb.component_base.base.mvp.inter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

/**
 * Created by yinbing on 2018/7/25 0025.
 */
public interface IView extends LifecycleOwner {

    Context getContext();

    void showError(String msg, String code);

    /**
     * 显示Dialog
     */
    void showHUD(String msg);

    /**
     * 关闭Dialog
     */
    void dismissHUD();
}
