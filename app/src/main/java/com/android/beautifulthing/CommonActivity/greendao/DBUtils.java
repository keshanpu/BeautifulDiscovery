package com.android.beautifulthing.CommonActivity.greendao;

import android.content.Context;

import com.android.beautifulthing.CommonActivity.greendao.gen.DaoMaster;
import com.android.beautifulthing.CommonActivity.greendao.gen.DaoSession;
import com.android.beautifulthing.CommonActivity.greendao.gen.UserDao;

/**
 * Created by yangjw on 2016/8/24.
 */
public class DBUtils {

    private static UserDao userDao;
    private DBUtils(){}
    public static UserDao getDao(Context context) {
        if (userDao == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "userdao_db", null);
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            userDao = daoSession.getUserDao();
        }

        return userDao;
    }
}
