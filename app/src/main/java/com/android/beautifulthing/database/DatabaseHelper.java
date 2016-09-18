package com.android.beautifulthing.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keshanpu on 16/9/18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mDb;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mDb = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user (_id integer PRIMARY KEY AUTOINCREMENT, name varchar(20), password varchar(50), flag int");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**数据库的插入
     * @param user
     */
    public void insertUser(User user){
        if (null != mDb){
            ContentValues cv = new ContentValues();
            cv.put("name", user.name);
            cv.put("password", user.password);
            cv.put("flag",user.flag);
            mDb.insert("user", "name", cv);
        }

    }

    /**获取当前表的游标
     * @return
     */
    public Cursor selectUserCursor(){
        Cursor cursor = null;
        if (mDb != null){
            cursor = mDb.query("user",null,null,null,null,null,null);
        }

        return cursor;
    }


    /**
     * @return 返回所有用户的集合;
     */
    public List<User> selectUserList(){
        List<User> userList = new ArrayList<>();
        if(mDb != null){
            Cursor cursor = mDb.query("user",null,null,null,null,null,"name DESC");
            int columnIndex = 0;
            while (cursor.moveToNext()){
                User user = new User();
                columnIndex = cursor.getColumnIndex("name");
                String name = cursor.getString(columnIndex);
                user.name = name;
                columnIndex = cursor.getColumnIndex("password");
                String password = cursor.getString(columnIndex);
                user.password = password;
                columnIndex = cursor.getColumnIndex("flag");
                int flag = cursor.getInt(columnIndex);
                user.flag = flag;
                userList.add(user);
            }
            cursor.close();
        }
        return userList;
    }

    /**更新用户数据
     * @param user
     */
    public void updateUser(User user){
        if(mDb != null){
            ContentValues cv = new ContentValues();
            cv.put("name", user.name);
            cv.put("password", user.password);
            cv.put("flag", user.flag);
            //更新数据库中数据
            mDb.update("student", cv, "name = ?", new String[]{user.name});
        }
    }

}
