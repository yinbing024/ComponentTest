package com.krb.shop_mall.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.krb.component_base.base.mvc.BaseVcActivity;
import com.krb.component_base.util.L;
import com.krb.component_data.d_arouter.RouterURLS;
import com.krb.shop_mall.R;
import com.krb.shop_mall.R2;
import com.krb.shop_mall.arouter.RouterCenter;

import butterknife.BindView;

@Route(path = RouterURLS.BASE_MAIN)
public class MainActivity extends BaseVcActivity implements View.OnClickListener {

    @BindView(R2.id.tv_get_data)
    Button mTvGetData;
    @BindView(R2.id.tv_get_data_mvc)
    Button mTvGetDataMVC;

    @BindView(R2.id.layout_main)
    LinearLayout mLayoutMain;
    @BindView(R2.id.v_title_container)
    LinearLayout vTitleContainer;
    @BindView(R2.id.title_back)
    TextView mBack;
    @BindView(R2.id.title_content_text)
    TextView mTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_main;
    }

    @Override
    protected void initTitle() {
        mLayoutMain.setBackgroundColor(getResources().getColor(R.color.windowBg));
        vTitleContainer.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mBack.setVisibility(View.GONE);
        mTitle.setText("欢迎您的到来!");
    }

    @Override
    protected void initView() {
        mTvGetData.setOnClickListener(this);
        mTvGetDataMVC.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tv_get_data){
            L.d("点击mvp测试");
        }else if(view.getId() == R.id.tv_get_data_mvc){
            L.d("点击mvc测试");
            RouterCenter.toMVCTest();
        }
    }
}
