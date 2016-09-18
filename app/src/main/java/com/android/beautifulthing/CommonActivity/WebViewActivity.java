package com.android.beautifulthing.CommonActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.beautifulthing.R;

/**
 * Created by ydy on 2016/9/12
 */
public class WebViewActivity extends AppCompatActivity{

    private String shop_name;
    private String shop_url;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_view);
        //获取传值
        getIntentMsg();
        //初始化视图
        initWebView();
    }

    /**
     * 初始化视图
     */
    private void initWebView() {
        //初始化
        TextView mShopName = (TextView) findViewById(R.id.webview_shop_name);
        mWebView = (WebView) findViewById(R.id.website_web_view);
        final ImageButton browserBack = (ImageButton) findViewById(R.id.browser_back);
        final ImageButton browserForward = (ImageButton) findViewById(R.id.browser_forward);
        //填值
        mShopName.setText(shop_name);
        browserBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        browserBack.setBackgroundResource(R.drawable.browser_back_pressed);
                        break;
                    case MotionEvent.ACTION_UP:
                        browserBack.setBackgroundResource(R.drawable.browser_back_normal);
                        break;
                }
                return false;
            }
        });
        browserForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        browserForward.setBackgroundResource(R.drawable.browser_forward_pressed);
                        break;
                    case MotionEvent.ACTION_UP:
                        browserForward.setBackgroundResource(R.drawable.browser_forward_normal);
                        break;
                }
                return false;
            }
        });
        //加载WebView
        loadWebView();
    }

    /**
     * 加载WebView
     */
    private void loadWebView() {
        //WebView，加载网址
        mWebView.loadUrl(shop_url);
        //优化
        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        mWebView.setWebViewClient(webViewClient);
    }

    /**
     * 获取传来的shop_name、shop_url
     */
    private void getIntentMsg() {
        Intent intent = getIntent();
        shop_name = intent.getStringExtra("shop_name");
        shop_url = intent.getStringExtra("shop_url");
    }

    /**
     * 界面控件点击事件
     */
    public void click(View view) {
        switch (view.getId()){
            case R.id.website_back://退出界面
                finish();
                break;
            case R.id.webview_reflesh://刷新界面
                loadWebView();
                break;
        }
    }
}
