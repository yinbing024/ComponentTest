package com.krb.shop_mall.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.krb.shop_mall.R;
import com.krb.shop_mall.arouter.RouterCenter;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mall_activity_splash);
        initView();
    }

    private void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RouterCenter.toMain();
                finish();
            }
        }).start();
    }
}
