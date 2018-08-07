package com.krb.component_base.base.mvc.inter;

/**
 * Created by yinbing on 2018/8/2 0002.
 */
public interface ILoadingDialogView {
    /**
     * 显示Dialog
     */
    void showHUD(String msg);

    /**
     * 关闭Dialog
     */
    void dismissHUD();
}
