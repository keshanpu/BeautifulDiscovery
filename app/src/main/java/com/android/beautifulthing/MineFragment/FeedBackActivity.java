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
 * Created by Administrator on 2016/9/13
 */
public class FeedBackActivity extends AppCompatActivity {
    private Context mContent;
    private RelativeLayout mrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mContent =this;
        mrl = (RelativeLayout) findViewById(R.id.feedback_activity_rl);
        mrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContent,FeedBackdetilActivity.class);
                mContent.startActivity(intent);
            }
        });
    }
}
