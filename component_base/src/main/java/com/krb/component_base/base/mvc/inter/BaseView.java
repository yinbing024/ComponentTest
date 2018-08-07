package com.krb.component_base.base.mvc.inter;

/**
 * Created by yinbing on 2018/8/2 0002.
 */
public interface BaseView extends ILoadingDialogView {
    void showError(String msg, String code);
}
