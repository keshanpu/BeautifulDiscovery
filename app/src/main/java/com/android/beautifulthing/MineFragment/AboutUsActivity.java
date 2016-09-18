package com.android.beautifulthing.MineFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.beautifulthing.CommonActivity.WebViewActivity;
import com.android.beautifulthing.R;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class AboutUsActivity extends AppCompatActivity {
    private Context mContext;
    private String weibo_url="http://weibo.com/u/5967200528?topnav=1&wvr=6&topsug=1&is_hot=1";
    private String zuimei_url="http://hi.zuimeia.com/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mContext =this;

    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.activity_about_us_weibo:
            case R.id.activity_about_us_tv1:;
                Intent intent =new Intent(mContext, WebViewActivity.class);
                intent.putExtra("shop_url",weibo_url);
                mContext.startActivity(intent);
                break;
            case R.id.activity_about_us_weixin:
            case R.id.activity_about_us_tv2:
                Toast.makeText(AboutUsActivity.this, "已经剪切", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_about_us_zuimei:
            case R.id.activity_about_us_tv3:
                Intent intent2 =new Intent(mContext, WebViewActivity.class);
                intent2.putExtra("shop_url",zuimei_url);
                intent2.putExtra("shop-name","最美团队");
                mContext.startActivity(intent2);
                break;
        }
    }

}

