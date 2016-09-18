package com.android.beautifulthing.database;

import android.content.Context;

import java.util.List;

/**
 * Created by keshanpu on 16/9/18.
 */
public class DatabaseTool {

    public static DatabaseHelper helper;
    public static boolean currentLoginFlag;

    /** 调用此方法,客户点击登陆按钮后,先去数据库中判断是否有此用户(根据用户名和密码)
     * 如果用户名和密码不正确,则提示登陆失败;
     * 如果用户名和密码正确,则将该用户的登陆标识设置为true
     * @param context
     * @param name
     * @param password
     * @return
     */
    public static boolean login(Context context, String name, String password){
        boolean flag = false;
        helper = new DatabaseHelper(context, "user_db",null,1);
        List<User> userList = helper.selectUserList();
        for (User user : userList){
            //用户名和密码都填写正确则登陆成功;
            if (user.name.equals(name) && user.password.equals(password)){
                user.flag = 1;//1表示登陆;//做预留,暂时无用,我们暂时不涉及多用户登陆;

                currentLoginFlag = true;//true表示登陆;
                //更新数据库;
                helper.updateUser(user);//更新当前user;
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 判断两次输入密码是否相同;
     * 相同,判断数据库中是否有此用户名,没有此用户名则注册成功;插入数据库;
     *
     * @param context
     * @param name
     * @param password
     * @param repeatPassword
     * @return
     */
    public static boolean register(Context context, String name, String password, String repeatPassword){
        boolean flag = false;

        helper = new DatabaseHelper(context, "user_db",null,1);

        List<User> userList = helper.selectUserList();
        if (password.equals(repeatPassword)){

            for (User user: userList){
                if (!user.name.equals(name)){
                    User newUser = new User();
                    newUser.name = name;
                    newUser.password = password;
                    newUser.flag = 0;//注册成功假设 需要重新登陆;
                    helper.insertUser(newUser);
                    flag = true;
                }
            }
        }
        return flag;

    }
    //http://zuimeia.com/apk/com.brixd.niceapp?source=landingpage

}
