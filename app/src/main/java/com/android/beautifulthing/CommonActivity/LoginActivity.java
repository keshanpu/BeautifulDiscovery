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
public class LoginActivity extends AppCompatActivity{

    public EditText username;
    public EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        initView();
    }

    private void initView() {
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        username.setText(name);
    }

    public void click(View view){
        switch (view.getId()) {
            case R.id.login_back_btn://退出
                finish();
                break;
            case R.id.login_turn_to_register://跳转到注册界面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
                break;
            case R.id.login_login_btn:
                //登陆按钮
                String name = username.getText().toString();
                String pwd = password.getText().toString();
                boolean flag = DatabaseTool.login(this, name, pwd);
                if(flag){
                    Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
