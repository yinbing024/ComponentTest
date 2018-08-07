package com.krb.shop_mall.api;

import com.krb.component_base.net.HttpHelper;
import com.krb.component_base.net.common_callback.INetCallback;
import com.krb.component_base.util.RxUtils;
import com.krb.component_data.bean.BaseObj;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public class ModelVpService {

    private ModelVpService mMvpService;

    /**
     * 获取api的回调
     * @param <T>
     */
    public interface MethodSelect<T>{

        Observable<BaseObj<T>> selectM(ApiService service);
    }

    public ModelVpService(ModelVpService modelService) {
        mMvpService = modelService;
    }

    public static <T> Observable<BaseObj<T>> getRemoteDataVp(MethodSelect<T> select) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(1)
                .create(ApiService.class))
                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
                ;
//                .subscribeWith(new BaseVpObserver<BaseObj<T>>() {
//                                   @Override
//                                   protected void onNextSuccess(BaseObj<T> tBaseObj) {
//
//                                       callback.onSuccess(tBaseObj.getData());
//                                   }
//                               }
//                );
    }
}
