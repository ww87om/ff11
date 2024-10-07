package com.example.yum.dao;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yum.db.DBUntil;
import com.example.yum.bean.UserBean;
import com.example.yum.bean.UserCommentBean;

/**
 * 操作数据库
 */
public class MerchantDao {
    public static SQLiteDatabase db = DBUntil.con;

    /**
     * 实现保存商家
     * @param phone
     * @param name
     * @param pwd
     * @param loc
     * @param add
     * @param des
     * @param img
     * @return
     */
    public static int saveMerchantInfo(long phone, String name, String pwd, String loc, String add, String des, String img) {
        // 检查手机号是否已被注册
        Cursor cursor = db.rawQuery("SELECT * FROM info_merchant WHERE m_phone = ?", new String[]{String.valueOf(phone)});
        if (cursor.moveToFirst()) {
            // 手机号已注册
            cursor.close();
            return -1; // 返回-1表示手机号已被注册
        }
        cursor.close();

        String data[] = {String.valueOf(phone), name, pwd, loc, add, des, img};

        try {
            db.execSQL("INSERT INTO info_merchant (m_phone, m_name, m_pwd, m_loc, m_add, m_des, m_img) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)",
                    data);
            return 1; // 返回1表示成功
        } catch (Exception e) {
            return 0; // 返回0表示失败
        }
    }



}
