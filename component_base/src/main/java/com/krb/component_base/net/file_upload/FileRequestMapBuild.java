package com.krb.component_base.net.file_upload;

import com.krb.component_base.net.params.RequestMapBuild;

import java.io.File;

import okhttp3.MultipartBody;

/**
 * Created by yinbing on 2018/8/7 0007.
 */
public interface FileRequestMapBuild extends RequestMapBuild<MultipartBody> {
    FileRequestMapBuild put(String key, File file);
    FileRequestMapBuild addAllProgressCallBack(ProgressResponseCallBack callBack);
    //新东西太多学不过来啊
}
