package com.krb.shop_mall.presenter;

import com.krb.component_base.base.mvp.BasePresenter;
import com.krb.component_base.base.mvp.BaseVpObserver;
import com.krb.component_base.net.file_upload.FileRequestMapParams;
import com.krb.component_base.net.params.RequestMapParams;
import com.krb.component_base.util.L;
import com.krb.component_data.bean.BaseObj;
import com.krb.shop_mall.bean.FeedArticleListData;
import com.krb.shop_mall.contract.MainContract;
import com.krb.shop_mall.model.MainMvpModel;

import okhttp3.MultipartBody;

/**
 * Created by yinbing on 2018/8/7 0007.
 */
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model> implements MainContract.Presenter {
    @Override
    protected MainContract.Model createModel() {
        return new MainMvpModel();
    }

    @Override
    public void initFeedArticleList() {
        FileRequestMapParams fileParam = new FileRequestMapParams(); //文件上传
        fileParam.put("file", "path");
        MultipartBody build = fileParam.build();
        RequestMapParams params = new RequestMapParams();
        params.put("key", "");
        addDisposable(
                mModel.getFeedArticleList(0, params)
                        .subscribeWith(new BaseVpObserver<BaseObj<FeedArticleListData>>(mView) {
                            @Override
                            public void onNext(BaseObj<FeedArticleListData> feedArticleListDataBaseObj) {
                                L.d("成功返回数据" + feedArticleListDataBaseObj.getData().getCurPage());
                                mView.showTestData(feedArticleListDataBaseObj.getData());
                            }
                        }));
    }
}
