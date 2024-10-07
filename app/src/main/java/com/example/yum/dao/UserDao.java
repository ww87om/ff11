package com.example.yum.dao;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yum.db.DBUntil;

/**
 * 操作数据库
 */
public class UserDao {
    public static SQLiteDatabase db = DBUntil.con;

    /**
     * 实现保存用户信息
     *
     * @param phone
     * @param name
     * @param pwd
     * @param gender
     * @param location
     * @return
     */
    public static int saveUserInfo(long phone, String name, String pwd, String gender, String location, String path) {
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM info_user WHERE u_phone = ?", new String[]{String.valueOf(phone)});
            if (cursor.moveToFirst()) {
                return -1; // 手机号已注册
            }

            String data[] = {String.valueOf(phone), name, pwd, gender, location, path};
            db.execSQL("INSERT INTO info_user (u_phone, u_name, u_pwd, u_gender, u_location, u_img) VALUES (?, ?, ?, ?, ?, ?)", data);
            return 1; // 成功
        } catch (Exception e) {
            e.printStackTrace(); // 记录异常
            return 0; // 失败
        } finally {
            if (cursor != null) {
                cursor.close(); // 确保关闭 Cursor
            }
        }
    }
}