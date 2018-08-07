package com.krb.component_base.net.common_callback;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
@FunctionalInterface
public interface INetCallback<T> {
    void onSuccess(T result);
}
