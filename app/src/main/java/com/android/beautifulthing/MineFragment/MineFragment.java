package com.android.beautifulthing.MineFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.beautifulthing.CommonActivity.tools.ApkDownLoad;
import com.android.beautifulthing.DesignerFragment.MyPopWindow;
import com.android.beautifulthing.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ydy on 2016/9/5
 */
public class MineFragment extends Fragment {

    public static final String APK_PATH = "http://zuimeia.com/apk/com.brixd.niceapp?source=landingpage";

    private Context mContext;
    private Button mbtn;
    private CircleImageView mset;
    private CircleImageView mLogin;
    private TextView mtvL;
    private RelativeLayout collection;
    private RelativeLayout attention;
    private RelativeLayout wish;
    private RelativeLayout beautiful;
    private PopupWindow mPWindow;

    public static MineFragment newInstance(){
        return new MineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_mine_main,container,false);
        //初始化界面
        initView(view);
        //点击事件
        clickManager();
        return view;
    }

    /**
     * 初始化界面
     */
    private void initView(View view) {
        //反馈
        mbtn = (Button)view.findViewById(R.id.fragment_mine_feedback_btn);
        //设置
        mset = (CircleImageView) view.findViewById(R.id.fragment_mine_set_iv);
        //登录
        mLogin = (CircleImageView) view.findViewById(R.id.fragment_mine_login_iv);
        mtvL = (TextView) view.findViewById(R.id.fragment_mine_login_tv);
        //收藏的画报
        collection = (RelativeLayout) view.findViewById(R.id.fragment_mine_main_rl1);
        //关注的设计师
        attention = (RelativeLayout) view.findViewById(R.id.fragment_mine_main_rl2);
        //我的心愿单
        wish = (RelativeLayout) view.findViewById(R.id.fragment_mine_main_rl3);
        //最美应用
        beautiful = (RelativeLayout) view.findViewById(R.id.fragment_mine_main_rl4);
    }

    /**
     * 点击事件
     */
    private void clickManager() {
        //反馈
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,FeedBackActivity.class);
                mContext.startActivity(intent);
            }
        });
        //设置
        mset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SettingActivity.class);
                mContext.startActivity(intent);
            }
        });
        //登录：图标
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        //登录：文字
        mtvL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        //收藏的画报
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        //关注的设计师
        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        //我的心愿单
        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        //最美的另一个产品：最美应用
        beautiful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApkDownLoad downLoad = new ApkDownLoad(mContext);
                downLoad.apkDownload(APK_PATH);
            }
        });
    }

}
