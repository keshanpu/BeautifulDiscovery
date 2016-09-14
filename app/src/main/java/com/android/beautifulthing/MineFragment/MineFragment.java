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

import com.android.beautifulthing.DesignerFragment.MyPopWindow;
import com.android.beautifulthing.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ydy on 2016/9/5.
 */
public class MineFragment extends Fragment {
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
        MineFragment mineFragment = new MineFragment();
        return mineFragment;
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
        mbtn = (Button)view.findViewById(R.id.fragment_mine_main_btn);
        mset = (CircleImageView) view.findViewById(R.id.fragment_mine_main_set);
        mLogin = (CircleImageView) view.findViewById(R.id.fragment_mine_main_civ);
        mtvL = (TextView) view.findViewById(R.id.fragment_mine_main_tv);
        collection = (RelativeLayout) view.findViewById(R.id.fragment_mine_main_rl1);
        attention = (RelativeLayout) view.findViewById(R.id.fragment_mine_main_rl2);
        wish = (RelativeLayout) view.findViewById(R.id.fragment_mine_main_rl3);
        beautiful = (RelativeLayout) view.findViewById(R.id.fragment_mine_main_rl4);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,FeedBackActivity.class);
                mContext.startActivity(intent);
            }
        });
        mset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SettingActivity.class);
                mContext.startActivity(intent);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
//                        initPopupWindow();
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
//                    initPopupWindow();
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        mtvL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
//                        initPopupWindow();
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
//                    initPopupWindow();
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
//                        initPopupWindow();
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
//                    initPopupWindow();
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
//                        initPopupWindow();
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
//                    initPopupWindow();
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPWindow!=null){
                    if (mPWindow.isShowing()){
                        mPWindow.dismiss();
                    }else {
//                        initPopupWindow();
                        MyPopWindow.initPopupWindow(mContext);
                    }
                }else {
//                    initPopupWindow();
                    MyPopWindow.initPopupWindow(mContext);
                }
            }
        });
        beautiful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

}
