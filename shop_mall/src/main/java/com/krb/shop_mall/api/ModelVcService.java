package com.krb.shop_mall.api;

import com.krb.component_base.base.mvc.BaseVcObserver;
import com.krb.component_base.base.mvp.inter.IView;
import com.krb.component_base.util.RxUtils;
import com.krb.component_base.net.HttpHelper;
import com.krb.component_base.net.common_callback.INetCallback;
import com.krb.component_base.util.L;
import com.krb.component_data.bean.BaseObj;
import com.krb.component_data.bean.BaseResponse;

import io.reactivex.Observable;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public class ModelVcService {

    private ModelVcService mModelService;
    private INetCallback mCallback;

    /**
     * 获取api的回调
     * @param <T>
     */
    public interface MethodSelect<T>{

        Observable<BaseObj<T>> selectM(ApiService service);
    }

    public ModelVcService(ModelVcService modelService) {
        mModelService = modelService;

    }

    public static <T> BaseVcObserver<BaseObj<T>> getRemoteData(boolean isShowHUD, IView mView, MethodSelect<T> select, INetCallback<T> callback) {
        return select.selectM(HttpHelper.getDefault(1).create(ApiService.class))
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseVcObserver<BaseObj<T>>(mView,isShowHUD) {
                    @Override
                    public void onNext(BaseObj<T> result) {
                        L.d("获取message", ":" + result.getMessage());
                        if (BaseResponse.SUCCESS.equals(result.getCode())) {
                            callback.onSuccess(result.getData());
                        } else {
                            mView.showError(result.getMessage(), result.getCode());
                        }
                    }
                });
    }
}
