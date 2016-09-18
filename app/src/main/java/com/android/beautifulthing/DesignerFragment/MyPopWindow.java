package com.android.beautifulthing.DesignerFragment;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.beautifulthing.R;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class MyPopWindow {
    private static ImageButton qqLogin;
    private static ImageButton sinaLogin;
    private static ImageButton wenxinLogin;
    private static PopupWindow mPWindow;

//    public MyPopWindow(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    private static Context mContext;
//    public static MyPopWindow init(){
//       MyPopWindow myPopWindow = new MyPopWindow(mContext);
//        return myPopWindow;
//    }


    public static void initPopupWindow(final Activity mContext) {
        View windowView = LayoutInflater.from(mContext).inflate(R.layout.pop_window,null,false);
        //ButterKnife.bind(mContext,windowView);
        qqLogin= (ImageButton) windowView.findViewById(R.id.pop_window_qq);
        qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "qq", Toast.LENGTH_SHORT).show();
            }
        });
        wenxinLogin= (ImageButton) windowView.findViewById(R.id.pop_window_wenxin);
        wenxinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "wen", Toast.LENGTH_SHORT).show();
            }
        });
        sinaLogin= (ImageButton) windowView.findViewById(R.id.pop_window_sina);
        sinaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "sina", Toast.LENGTH_SHORT).show();
            }
        });
        mPWindow = new PopupWindow(windowView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置参数，让点击屏幕空白区域，可以使PopupWindow消失
        mPWindow.setBackgroundDrawable(new ColorDrawable());
        mPWindow.setOutsideTouchable(false);
        mPWindow.setFocusable(true);
        // 设置动画效果
        WindowManager.LayoutParams params = mContext.getWindow().getAttributes();
        params.alpha=0.3f;
        mContext.getWindow().setAttributes(params);
        //点击其他地方消失
        mPWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = mContext.getWindow().getAttributes();
                params.alpha=1f;
               mContext.getWindow().setAttributes(params);
            }
        });


        /**
         * 参数：
         * 1，传入一个View，最后showAtLocation会以这个View的父容器为参照，决定显示位置
         * 2，重力方向的设置，参照物是第一个参数的父容器
         * 3，x轴上的偏移量
         * 4，y轴上的偏移量
         */
        mPWindow.showAtLocation(windowView, Gravity.CENTER, 0, 50);
    }
}
