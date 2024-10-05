package com.example.yum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.yum.activity.merchant.RegisterMerchantActivity;
import com.example.yum.activity.user.RegisterUserActivity;
import com.example.yum.db.DBUntil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实现数据插入
        DBUntil dbUntil=new DBUntil(this);
        DBUntil.con= dbUntil.getWritableDatabase();
        //实现跟共享数据
//        SharedPreferences sharedPreferences=getSharedPreferences("data", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sharedPreferences.edit();


        //实现注册跳转
        RadioButton loginMerchant = findViewById(R.id.login_merchant);
        RadioButton loginUser = findViewById(R.id.login_user);
        loginUser.setChecked(true);//让运行时候商家单选按钮默认选择
        Button registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loginMerchant.isChecked()) {
                    // 进入商家注册界面
                    startActivity(new Intent(MainActivity.this, RegisterMerchantActivity.class));
                } else if (loginUser.isChecked()) {
                    // 进入用户注册界面
                    startActivity(new Intent(MainActivity.this, RegisterUserActivity.class));
                } else {
                    // 提示用户选择类型的逻辑
                }

            }
        });



//        //登陆功能
//
//        EditText accountText=findViewById(R.id.login_account);
//        EditText pwdText=findViewById(R.id.login_pwd);
//
//
//        Button denglu=findViewById(R.id.login_denglu);
//
//        RadioButton role=findViewById(R.id.login_sj);
//
//        denglu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String account=accountText.getText().toString();
//                String pwd=pwdText.getText().toString();
//                if( account.isEmpty()){
//                    Toast.makeText(MainActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
//                }else if(pwd.isEmpty()){
//                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
//                }else{
//                    edit.putString("account",account);
//                    edit.apply();
//                    if(role.isChecked()){
//                        //管理员
//                        int a= AdminDao.loginBusiness(account,pwd);
//                        if(a==1){
//                            Toast.makeText(MainActivity.this, "管理员登录成功", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(MainActivity.this, ManageManActivity.class);
//                            startActivity(intent);
//                        }else{
//                            Toast.makeText(MainActivity.this, "管理员账号或密码错误", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }else{
//                        int a= AdminDao.loginUser(account,pwd);
//                        if(a==1){
//                            Toast.makeText(MainActivity.this, "用户登录成功", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(MainActivity.this, ManageUserActivity.class);
//                            startActivity(intent);
//                        }else{
//                            Toast.makeText(MainActivity.this, "用户账号或密码错误", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//
//                }
//
//            }
//        });



    }
}