package com.krb.component_base.base.mvp.inter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;

/**
 * Created by yinbing on 2018/7/25 0025.
 */
public interface IPresenter <V extends IView> extends LifecycleObserver {

    /**
     * 创建view时调用  调用在initView 之后
     * @param view
     */
    void attachView(V view);

    /**
     * view销毁时调用释放资源
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void detachView();

    Context getContext();
}