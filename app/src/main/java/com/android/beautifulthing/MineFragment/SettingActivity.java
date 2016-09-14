package com.android.beautifulthing.MineFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.beautifulthing.R;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class SettingActivity extends AppCompatActivity{
    private RelativeLayout setting;
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mContext = this;
        setting = (RelativeLayout) findViewById(R.id.fragment_mine_main_rl6);

    }

    public void click(View view) {
        Intent intent = new Intent(mContext,AboutUsActivity.class);
        mContext.startActivity(intent);
    }

    public void click1(View view) {
        Intent intent = new Intent(mContext,FeedBackActivity.class);
        mContext.startActivity(intent);
    }
}
