package com.example.yum.activity.user;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yum.MainActivity;
import com.example.yum.R;
import com.example.yum.activity.merchant.RegisterMerchantActivity;
import com.example.yum.dao.UserDao;
import com.example.yum.until.FileImgUntil;

public class RegisterUserActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> getContentLauncher;
    private Uri uri;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // 实现返回功能
        Toolbar toolbar = findViewById(R.id.register_user_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // 返回
            }
        });

        ImageView imgText=findViewById(R.id.register_user_img);
        getContentLauncher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result!=null){
                    imgText.setImageURI(result);
                    uri =result;

                }else{
                    Toast.makeText(RegisterUserActivity.this, "未选择头像", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 获取头像
        Drawable defaultDrawable = ContextCompat.getDrawable(this, R.drawable.user); // 默认头像
        ImageView userImg = findViewById(R.id.register_user_img);

        // 注册获取内容的启动器
        getContentLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    userImg.setImageURI(result);
                    uri = result;
                } else {
                    Toast.makeText(RegisterUserActivity.this, "未选择头像", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 用户信息输入
        EditText phoneText = findViewById(R.id.register_user_phone);
        EditText nameText = findViewById(R.id.register_user_name);
        EditText passwordText = findViewById(R.id.register_user_password);
        RadioGroup genderGroup = findViewById(R.id.register_user_gender);
        sex="女";
        RadioButton man=findViewById(R.id.register_user_man);
        man.setChecked(true);

        Spinner userAddressSpinner = findViewById(R.id.register_user_location);

        // 定义城市列表，包括提示项
        String[] cities = {
                "请选择你当前所在城市",  // 提示项
                "河北", "山西", "辽宁", "吉林", "黑龙江",
                "江苏", "浙江", "安徽", "福建", "江西",
                "山东", "河南", "湖北", "湖南", "广东",
                "广西", "海南", "四川", "贵州", "云南",
                "西藏", "陕西", "甘肃",
                "新疆", "内蒙古", "宁夏", "西藏",
                "北京", "上海", "天津", "重庆",
                "香港", "澳门", "台湾"
        };

        // 创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userAddressSpinner.setAdapter(adapter);

        // 点击头像选择图片
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(v);
            }
        });

        // 注册按钮点击事件
        Button reg = findViewById(R.id.register_user_Btn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneStr = phoneText.getText().toString();
                String name = nameText.getText().toString();
                String password = passwordText.getText().toString();
                String location = userAddressSpinner.getSelectedItem().toString();

                if (phoneStr.isEmpty() || phoneStr.length() != 11) {
                    Toast.makeText(RegisterUserActivity.this, "请输入11位有效的电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (name.isEmpty()) {
                    Toast.makeText(RegisterUserActivity.this, "请输入用户昵称", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(RegisterUserActivity.this, "请输入用户密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (location.equals("请选择你当前所在城市")) {
                    Toast.makeText(RegisterUserActivity.this, "请选择你当前所在城市", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedGenderId = genderGroup.getCheckedRadioButtonId();
                RadioButton selectedGenderButton = findViewById(selectedGenderId);
                String gender = selectedGenderButton != null ? selectedGenderButton.getText().toString() : "未知";

                // 保存用户信息
                // 假设 UserDao.saveUserInfo() 是保存用户信息的方法
                long phone = Long.parseLong(phoneStr);
                // 检查手机号是否已注册
                int checkResult = UserDao.saveUserInfo(phone, name, password, gender, location, ""); // 初步保存以检查手机号

                if (checkResult == -1) {
                    Toast.makeText(RegisterUserActivity.this, "该手机号已被其他用户注册", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 添加保存头像的逻辑
                Drawable drawable=imgText.getDrawable();//获取当前标签的图片
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();//获取这个图片的二进制文件
                Bitmap bitmapDef = ((BitmapDrawable) defaultDrawable).getBitmap();//获取这个图片的二进制文件
                if(bitmap.sameAs(bitmapDef)){//判断是不是默认的图片
                    Toast.makeText(RegisterUserActivity.this, "请点击图片进行添加头像", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(man.isChecked()){
                    sex="男";
                }else{
                    sex="女";
                }
                String path = FileImgUntil.getImgName();
                FileImgUntil.saveImageBitmapToFileImg(uri, RegisterUserActivity.this, path);
                // 最终保存用户信息
                int finalResult = UserDao.saveUserInfo(phone, name, password, gender, location, path);
                if (finalResult == 1) {
                    Toast.makeText(RegisterUserActivity.this, "注册用户成功", Toast.LENGTH_SHORT).show();
                    // 启动主页面
                    Intent intent = new Intent(RegisterUserActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // 结束当前活动
                } else {
                    Toast.makeText(RegisterUserActivity.this, "注册用户失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //实现选择图片
        imgText.setOnClickListener(new View.OnClickListener() {//实现点击头像进行上传头像
            @Override
            public void onClick(View v) {
                //打开文件选择器
                openGallery(v);
            }
        });
    }

    /**
     * 打开文件选择器
     * @param v
     */
    private void openGallery(View v) {
        getContentLauncher.launch("image/*");
    }
}