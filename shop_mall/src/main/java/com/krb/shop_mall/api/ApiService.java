package com.krb.shop_mall.api;

import com.krb.component_data.bean.BaseObj;
import com.krb.shop_mall.bean.FeedArticleListData;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public interface ApiService {
    String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 首页获取feed文章列表
     *
     * @param num 页数
     * @return Observable<FeedArticleListResponse>
     */
    @GET("article/list/{num}/json")
    Observable<BaseObj<FeedArticleListData>> getFeedArticleList(@Path("num") int num);

    @GET("article/list/{num}/json")
    Observable<BaseObj<FeedArticleListData>> getFeedArticleList(@Path("num") int num, @QueryMap Map<String, String> map);
}
