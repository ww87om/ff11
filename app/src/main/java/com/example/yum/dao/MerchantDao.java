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

}
