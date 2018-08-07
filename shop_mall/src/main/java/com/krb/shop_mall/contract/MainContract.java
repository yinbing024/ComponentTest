package com.krb.shop_mall.contract;

import com.krb.component_base.base.mvp.inter.IModel;
import com.krb.component_base.base.mvp.inter.IPresenter;
import com.krb.component_base.base.mvp.inter.IView;
import com.krb.component_base.net.params.RequestMapParams;
import com.krb.component_data.bean.BaseObj;
import com.krb.shop_mall.bean.FeedArticleListData;

import io.reactivex.Observable;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public interface MainContract {

    interface View extends IView {

        void showTestData(FeedArticleListData feedArticleListData);

    }

    interface Presenter extends IPresenter<View> {

        void initFeedArticleList();
    }

    interface Model extends IModel {

        Observable<BaseObj<FeedArticleListData>> getFeedArticleList(int page, RequestMapParams params);
    }
}
