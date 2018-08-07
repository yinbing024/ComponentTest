package com.krb.shop_mall.activity;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.krb.component_base.base.mvp.BaseVpActivity;
import com.krb.shop_mall.R;
import com.krb.shop_mall.R2;
import com.krb.shop_mall.bean.FeedArticleListData;
import com.krb.shop_mall.contract.MainContract;

import butterknife.BindView;

/**
 * Created by yinbing on 2018/8/6 0006.
 */
public class MvpTestActivity extends BaseVpActivity<MainContract.View, MainContract.Presenter> implements MainContract.View, View.OnClickListener {

    @BindView(R2.id.tv_test_mvp)
    Button mTvTestMvp;
    @BindView(R2.id.tv_data)
    TextView mTvData;
    @BindView(R2.id.tv_error)
    TextView mTvError;

    @Override
    public MainContract.Presenter createPresenter() {
        return null;
    }

    @Override
    public MainContract.View createView() {
        return this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_mvp_test;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        mTvTestMvp.setOnClickListener(this);
    }

    @Override
    public void showTestData(FeedArticleListData feedArticleListData) {
        mTvData.setText("成功获取"+feedArticleListData.getDatas().size()+"条数据");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_test_mvp){
            mPresenter.initFeedArticleList();
            // loadData(0,params);
        }
    }

}
