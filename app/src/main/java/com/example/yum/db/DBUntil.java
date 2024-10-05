package com.example.yum.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.ccf.feige.orderfood.R;
//import com.ccf.feige.orderfood.activity.user.RegisterUserActivity;
//import com.ccf.feige.orderfood.until.FileImgUntil;

/**
 * 链接数据库
 */
public class DBUntil extends SQLiteOpenHelper {
    private static final int version = 3;//版本号，每次更新表结构就会加1，否则不生效
    private static final  String databaseName="db_takeaway.db";//数据库名称必须以db结尾
    private  Context context;
    public static SQLiteDatabase con;//链接数据库的链接，通过他可以操作数据库
    public DBUntil(Context context) {
        super(context, databaseName, null, version,null);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys = false");//涉及到主、外键，让其失效

        //创建数据库
        db.execSQL("drop table if exists info_merchant");//如果这表存在则删除

        // 商家个人信息表
        db.execSQL("CREATE TABLE info_merchant (" +
                "m_id INTEGER PRIMARY KEY AUTOINCREMENT," + // 设置 m_id 为自增
                "m_phone INTEGER," +
                "m_name VARCHAR(20)," +
                "m_pwd VARCHAR(20)," +
                "m_loc VARCHAR(20)," +
                "m_add VARCHAR(20)," +
                "m_des VARCHAR(200)," +
                "m_img VARCHAR(255))"); // 存储图片路径

//        String path= FileImgUntil.getImgName();//获取一个存储图片的路径名字
//        Drawable defaultDrawable= ContextCompat.getDrawable(context, R.drawable.merchant);
//        Bitmap bitmapDef = ((BitmapDrawable) defaultDrawable).getBitmap();//获取这个图片的二进制文件
//        FileImgUntil.saveBitmapAsync(bitmapDef, path);//保存图片

        // 插入商家数据
        db.execSQL("INSERT INTO info_merchant (m_phone, m_name, m_pwd, m_loc, m_add, m_des, m_img) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                new Object[]{11111111111L, "默认商家1", "1234567", "上海", "上海市松江区", "经典美味", "A"});

        db.execSQL("INSERT INTO info_merchant (m_phone, m_name, m_pwd, m_loc, m_add, m_des, m_img) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                new Object[]{22222222222L, "默认商家2", "1234567", "上海", "上海市浦东区", "百年老店","B"});


//        db.execSQL("drop table if exists d_user");//如果这表存在则删除
//        db.execSQL("create table d_user(s_id varchar(20) primary key," +
//                "s_pwd varcahr(20)," +
//                "s_name varcahr(20)," +
//                "s_sex varcahr(200)," +
//                "s_address varcahr(200)," +
//                "s_phone varcahr(20)," +
//                "s_img varchar(255))");//存储是图片路径
//
//        db.execSQL("INSERT INTO d_user (s_id, s_pwd, s_name,s_sex, s_address, s_phone, s_img) " +
//                        "VALUES (?, ?, ?,?, ?,?, ?)",
//                new Object[]{"admin", "123456", "小铭用户", "男", "北京市","12312312", path});
//
//
//        //写一个存储食物的表
//        db.execSQL("drop table if exists d_food");//如果这表存在则删除
//        db.execSQL("create table d_food(s_food_id varchar(20) primary key," +
//                "s_business_id varcahr(20)," +
//                "s_food_name varcahr(20)," +
//                "s_food_des varcahr(200)," +
//                "s_food_price varcahr(200)," +
//                "s_food_img varchar(255))");//存储是图片路径
//
//
//        String foodImg= FileImgUntil.getImgName();//获取一个存储图片的路径名字
//        FileImgUntil.saveSystemImgToPath(context,R.drawable.mlt,foodImg);
//        db.execSQL("INSERT INTO d_food (s_food_id, s_business_id, s_food_name,s_food_des, s_food_price, s_food_img) " +
//                        "VALUES (?, ?, ?,?,  ?, ?)",
//                new Object[]{"1", "root", "东北麻辣烫", "不吃就会后悔的麻辣烫", "19.88", foodImg});
//
//
//
//        db.execSQL("INSERT INTO d_food (s_food_id, s_business_id, s_food_name,s_food_des, s_food_price, s_food_img) " +
//                        "VALUES (?, ?, ?,?,  ?, ?)",
//                new Object[]{"3", "root", "东北麻辣烫", "不吃就会后悔的麻辣烫1", "18.88", foodImg});
//        db.execSQL("INSERT INTO d_food (s_food_id, s_business_id, s_food_name,s_food_des, s_food_price, s_food_img) " +
//                        "VALUES (?, ?, ?,?,  ?, ?)",
//                new Object[]{"4", "root", "东北麻辣烫", "不吃就会后悔的麻辣烫2", "17.88", foodImg});
//        db.execSQL("INSERT INTO d_food (s_food_id, s_business_id, s_food_name,s_food_des, s_food_price, s_food_img) " +
//                        "VALUES (?, ?, ?,?,  ?, ?)",
//                new Object[]{"5", "root", "东北麻辣烫", "不吃就会后悔的麻辣烫3", "16.88", foodImg});
//
//
//        String foodImg1= FileImgUntil.getImgName();//获取一个存储图片的路径名字
//        FileImgUntil.saveSystemImgToPath(context,R.drawable.klm,foodImg1);
//        db.execSQL("INSERT INTO d_food (s_food_id, s_business_id, s_food_name,s_food_des, s_food_price, s_food_img) " +
//                        "VALUES (?, ?, ?, ?, ?, ?)",
//                new Object[]{"2", "root", "东北烤冷面", "东北麻烤冷面,不吃就会后悔的烤冷面", "15.88", foodImg1});
//
//
//        //订单编号，订单时间，购买人id,商家id,订单详情id,收货地址，商品详情
//        db.execSQL("drop table if exists d_orders");//如果这表存在则删除
//        db.execSQL("create table d_orders(s_order_id varchar(20) primary key," +
//                "s_order_time varcahr(30)," +
//                "s_business_id varcahr(20)," +
//                "s_user_id varcahr(20)," +
//                "s_order_details_id varcahr(30)," +
//                "s_order_sta varcahr(30)," +
//                "s_order_address varchar(255))");//存储是图片路径
//
//        //s_order_sta 是大概有3种状态     1未处理订单  2取消订单  3完成的订单（结束订单）
//
//        db.execSQL("INSERT INTO d_orders (s_order_id, s_order_time, s_business_id,s_user_id, s_order_details_id,s_order_sta, s_order_address) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
//                new Object[]{"1", "2024-07-07 12:36:54", "root", "admin", "1","1", "小铭-11111-种花家，兔子区，110号"});
//
//        db.execSQL("INSERT INTO d_orders (s_order_id, s_order_time, s_business_id,s_user_id, s_order_details_id, s_order_sta,s_order_address) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
//                new Object[]{"2", "2024-07-06 12:36:54", "root", "admin", "2","2", "小铭-11111-种花家，兔子区，110号"});
//
//
//        db.execSQL("INSERT INTO d_orders (s_order_id, s_order_time, s_business_id,s_user_id, s_order_details_id, s_order_sta,s_order_address) " +
//                        "VALUES (?, ?, ?, ?, ?, ?,?)",
//                new Object[]{"3", "2024-07-09 11:36:54", "root", "admin", "3", "3","小铭-11111-种花家，兔子区，110号"});
//
//
//        //写一个存储食物的表
//        db.execSQL("drop table if exists d_order_details");//如果这表存在则删除
//        db.execSQL("create table d_order_details(s_details_id varchar(30)," +
//                "s_food_id varcahr(20)," +
//                "s_food_name varcahr(20)," +
//                "s_food_des varcahr(200)," +
//                "s_food_price varcahr(20)," +
//                "s_food_num varcahr(20)," +
//                "s_food_img varchar(255))");//存储是图片路径
//
//
//        db.execSQL("INSERT INTO d_order_details (s_details_id,s_food_id, s_food_name,s_food_des, s_food_price, s_food_num,s_food_img) " +
//                        "VALUES (?, ?,?, ?, ?, ?, ?)",
//                new Object[]{"1",  "1", "东北麻辣烫", "不吃就会后悔的麻辣烫",  "19.88","5", foodImg1});
//
//        db.execSQL("INSERT INTO d_order_details (s_details_id,s_food_id, s_food_name,s_food_des, s_food_price,s_food_num, s_food_img) " +
//                        "VALUES (?, ?, ?,?, ?, ?, ?)",
//                new Object[]{"1", "2", "东北烤冷面", "东北麻烤冷面,不吃就会后悔的烤冷面", "19.88", "10",foodImg1});
//
//
//        db.execSQL("INSERT INTO d_order_details (s_details_id,s_food_id, s_food_name,s_food_des, s_food_price, s_food_num,s_food_img) " +
//                        "VALUES (?, ?,?, ?, ?, ?, ?)",
//                new Object[]{"2",  "1", "东北麻辣烫", "不吃就会后悔的麻辣烫",  "19.88","5", foodImg1});
//
//        db.execSQL("INSERT INTO d_order_details (s_details_id,s_food_id, s_food_name,s_food_des, s_food_price,s_food_num, s_food_img) " +
//                        "VALUES (?, ?, ?,?, ?, ?, ?)",
//                new Object[]{"2", "2", "东北烤冷面", "东北麻烤冷面,不吃就会后悔的烤冷面", "19.88", "10",foodImg1});
//
//        db.execSQL("INSERT INTO d_order_details (s_details_id,s_food_id, s_food_name,s_food_des, s_food_price,s_food_num, s_food_img) " +
//                        "VALUES (?, ?, ?,?, ?, ?, ?)",
//                new Object[]{"3", "2", "东北烤冷面", "东北麻烤冷面,不吃就会后悔的烤冷面", "19.88", "110",foodImg1});
//
//
//
//
//        db.execSQL("drop table if exists d_comments");//如果这表存在则删除
//
//        db.execSQL("create table d_comments(s_comment_id varchar(20) primary key," +//评论ID
//                "s_comment_user_id varcahr(20)," +//评论用户的ID
//                "s_comment_business_id varcahr(20)," +//评论商家的ID
//                "s_comment_con varcahr(200)," +//评论内容
//                "s_comment_time varcahr(20)," +//评论时间
//                "s_comment_score varcahr(20)," +//评论分数
//                "s_comment_img varchar(255))");//评论图片 可有 可无
//        db.execSQL("INSERT INTO d_comments (s_comment_id,s_comment_user_id, s_comment_business_id" +
//                        ",s_comment_con, s_comment_time,s_comment_score,s_comment_img) " +
//                        "VALUES (?, ?, ?,?, ?, ?, ?)",
//                new Object[]{"1", "admin", "root", "这家吃过很多此很好吃", "2029-10-16 10:15", "5",foodImg1});
//
//        db.execSQL("INSERT INTO d_comments (s_comment_id,s_comment_user_id, s_comment_business_id" +
//                        ",s_comment_con, s_comment_time,s_comment_score,s_comment_img) " +
//                        "VALUES (?, ?, ?,?, ?, ?, ?)",
//                new Object[]{"2", "admin", "root", "一般不咋地", "2030-10-16 10:15", "1",foodImg1});
//
//        db.execSQL("INSERT INTO d_comments (s_comment_id,s_comment_user_id, s_comment_business_id" +
//                        ",s_comment_con, s_comment_time,s_comment_score,s_comment_img) " +
//                        "VALUES (?, ?, ?,?, ?, ?, ?)",
//                new Object[]{"3", "admin", "root", "吃了第一次还想顶", "2028-10-16 10:15", "3",foodImg1});
//
//
//
//
//
//        db.execSQL("drop table if exists d_address");//如果这表存在则删除
//
//        db.execSQL("create table d_address(s_id varchar(20) primary key," +//评论ID
//                "s_user_id varcahr(20)," +//用户账号
//                "s_user_name varcahr(20)," +//用户名称
//                "s_user_address varcahr(200)," +//用户地址
//                "s_user_phone varchar(255))");//联系方法
//        db.execSQL("INSERT INTO d_address (s_id,s_user_id, s_user_name,s_user_address" +
//                        ",s_user_phone) " +
//                        "VALUES (?, ?, ?,?, ?)",
//                new Object[]{"1", "admin", "小铭先生", "天津市 唐芳区1111号", "32132131"});
//
//        db.execSQL("INSERT INTO d_address (s_id,s_user_id,s_user_name, s_user_address" +
//                        ",s_user_phone) " +
//                        "VALUES (?, ?, ?,?, ?)",
//                new Object[]{"2", "admin", "小赵先生", "洛克市 高山区", "696969"});
//
//        db.execSQL("INSERT INTO d_address (s_id,s_user_id, s_user_name,s_user_address" +
//                        ",s_user_phone) " +
//                        "VALUES (?, ?, ?,?, ?)",
//                new Object[]{"3", "admin", "大头先生", "三花市 雾化叨叨", "99888"});

        db.execSQL("PRAGMA foreign_keys = true");//涉及到主、外键，让其失效
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }
}
