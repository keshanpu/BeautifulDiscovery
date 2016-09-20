package com.android.beautifulthing.CommonActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.beautifulthing.CommonActivity.greendao.DBUtils;
import com.android.beautifulthing.CommonActivity.greendao.entity.User;
import com.android.beautifulthing.CommonActivity.greendao.gen.UserDao;
import com.android.beautifulthing.MineFragment.LoginInfo;
import com.android.beautifulthing.R;

/**
 * Created by ydy on 2016/9/18.
 */
public class LoginActivity extends AppCompatActivity{

    private EditText username;
    private EditText password;



    private UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        initView();

        initData();
    }

    /**
     * 获取greendao
     */
    private void initData() {
        userDao = DBUtils.getDao(this);
    }

    private void initView() {
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
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
//                //登陆按钮
                String name = username.getText().toString();
                String pwd = password.getText().toString();

                User findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
                if(null != findUser) {
                    if (findUser.getPassword().equals(pwd)) {
                        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                        LoginInfo.current_login_flag = true;
                        Intent data = new Intent();
                        data.putExtra("str",findUser.getName()+"已登陆");
                        setResult(1001,data);
                        finish();
                        break;

                    } else {
                        Toast.makeText(this, "登陆失败,密码错误", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "登陆不成功,没有此用户", Toast.LENGTH_SHORT).show();
                }
                LoginInfo.current_login_flag = false;
                break;
        }
    }

}
