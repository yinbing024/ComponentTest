package com.krb.shop_mall.model;

import com.krb.component_base.net.params.RequestMapParams;
import com.krb.component_data.bean.BaseObj;
import com.krb.shop_mall.api.ApiService;
import com.krb.shop_mall.api.ModelVpService;
import com.krb.shop_mall.bean.FeedArticleListData;
import com.krb.shop_mall.contract.MainContract;

import io.reactivex.Observable;

/**
 * Created by yinbing on 2018/8/7 0007.
 */
public class MainMvpModel implements MainContract.Model{
    @Override
    public Observable<BaseObj<FeedArticleListData>> getFeedArticleList(int page, RequestMapParams params) {
        return ModelVpService.getRemoteDataVp(new ModelVpService.MethodSelect<FeedArticleListData>() {
            @Override
            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
                return service.getFeedArticleList(page, params.build());
            }
        });
    }
}
