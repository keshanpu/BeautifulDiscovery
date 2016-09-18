package com.android.beautifulthing.CommonActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.beautifulthing.R;
import com.android.beautifulthing.database.DatabaseTool;

/**
 * Created by ydy on 2016/9/18.
 */
public class RegisterActivity extends AppCompatActivity{

    public EditText username;
    public EditText password;
    public EditText repeat_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);
        initView();
    }

    private void initView() {
        username = (EditText) findViewById(R.id.register_username);
        password = (EditText) findViewById(R.id.register_password);
        repeat_password = (EditText) findViewById(R.id.register_repeat_password);
    }

    public void click(View view){
        switch (view.getId()) {
            case R.id.register_back_btn://退出界面
                finish();
                break;
            case R.id.register_register_btn://注册
//                Toast.makeText(RegisterActivity.this, "注册...", Toast.LENGTH_SHORT).show();

                String name = username.getText().toString();
                String pwd = password.getText().toString();
                String repeatPwd = repeat_password.getText().toString();
                boolean flag = DatabaseTool.register(this, name, pwd, repeatPwd);
                if (flag){
                    Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("username",name);
                    RegisterActivity.this.startActivity(intent);
                }
                break;
        }
    }

}
