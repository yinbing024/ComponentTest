package com.krb.component_data.bean;

import com.krb.component_data.constants.BaseEventbusBean;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public class EventBusBean extends BaseEventbusBean<Object> {
    public EventBusBean(int type, Object obj) {
        super(type, obj);
    }

    //例子
    public static final int CUSTOMER_FILTER = 10000;

    //首页侧滑
    public static final int SHOP_MALL_HOME = 10001;
}
