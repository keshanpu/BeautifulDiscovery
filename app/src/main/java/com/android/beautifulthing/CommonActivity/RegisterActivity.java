package com.android.beautifulthing.CommonActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.beautifulthing.R;

/**
 * Created by ydy on 2016/9/18.
 */
public class RegisterActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);
    }

    public void click(View view){
        switch (view.getId()) {
            case R.id.register_back_btn://退出界面
                finish();
                break;
            case R.id.register_register_btn://注册
                Toast.makeText(RegisterActivity.this, "注册...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
