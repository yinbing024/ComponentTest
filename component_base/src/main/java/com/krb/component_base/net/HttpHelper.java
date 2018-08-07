package com.krb.component_base.net;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.SparseArray;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.krb.component_base.BuildConfig;
import com.krb.component_base.util.NetUtils;
import com.krb.component_base.constants.BaseApplication;
import com.krb.component_data.constants.BaseHost;
import com.krb.component_data.constants.HostType;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public class HttpHelper {
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;
    //设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    //max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
    private static final String CACHE_CONTROL_AGE = "max-age=0";
    public static Retrofit retrofit;
    public OkHttpClient okHttpClient;

    //sparsearray 比 hashmap 更优化内存
    private static SparseArray<HttpHelper> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

    private HttpHelper(int hostType){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG){
            //开启Log
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(logInterceptor);
        }

        //缓存
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        Interceptor headerInterceptor =new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };

        builder.readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                // .addInterceptor(mRewriteCacheControlInterceptor)
                //  .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
        // .addInterceptor(headerInterceptor)
        ;
        // .cache(cache)
        // .build();

        okHttpClient = builder.build();

        //自定义Gson对象
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'ToastUtils'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseHost.getHost(hostType))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Retrofit getDefault(int hostType) {
        HttpHelper retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new HttpHelper(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }

        return retrofit;
    }

    /**
     * 根据网络状况获取缓存的策略
     * 使用方法:在api server 中添加请求头 @Header("Cache-Control") String cacheControl ,在调用传入getCacheControl()
     */
    @NonNull
    public static String getCacheControl() {
        return NetUtils.isNetConnected(BaseApplication.getAppContext()) ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            //检查网络状态
            if (!NetUtils.isNetConnected(BaseApplication.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl)? CacheControl.FORCE_NETWORK:CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtils.isNetConnected(BaseApplication.getAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置

                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
