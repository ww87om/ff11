package com.example.yum.activity.merchant;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
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
import android.widget.Spinner;
import android.widget.Toast;


import com.example.yum.MainActivity;
import com.example.yum.R;
import com.example.yum.activity.user.RegisterUserActivity;
import com.example.yum.dao.MerchantDao;
import com.example.yum.until.FileImgUntil;

public class RegisterMerchantActivity extends AppCompatActivity {
    private ActivityResultLauncher<String> getContentLauncher;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_merchant);

        //*实现返回功能
        Toolbar toolbar = findViewById(R.id.register_merchant_toolbar);
        setSupportActionBar(toolbar);
        // 两种返回方式，跳转或关闭
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();//返回
                //finish();//关闭当前界面，显示上一界面
            }
        });

        //*获取头像
        Drawable defaultDrawable = ContextCompat.getDrawable(this,R.drawable.merchant);//默认头像
        ImageView ImgText=findViewById(R.id.register_merchant_img);

        getContentLauncher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result!=null){
                    ImgText.setImageURI(result);
                    uri =result;

                }else{
                    Toast.makeText(RegisterMerchantActivity.this, "未选择头像", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //*电话号码
        EditText PhoneText=findViewById(R.id.register_merchant_phone);

        //*店铺名称
        EditText NameText=findViewById(R.id.register_merchant_name);

        //*店铺密码
        EditText PwdText=findViewById(R.id.register_merchant_password);

        //*实现选择店铺所在省份功能
        // 获取 Spinner
        Spinner LocSpinner = findViewById(R.id.register_merchant_location);
        // 定义省份和地区，包括提示项
        String[] provincesAndRegions = {
                "请选择店铺所在地",  // 提示项
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
                android.R.layout.simple_spinner_item, provincesAndRegions);
        // 设置下拉菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 设置适配器到 Spinner
        LocSpinner.setAdapter(adapter);

        //*店铺详细地址
        EditText AddText=findViewById(R.id.register_merchant_address);
        //*店铺描述
        EditText DesText=findViewById(R.id.register_merchant_description);

        //*点击默认头像上传图片
        ImgText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(v);
            }
        });

        //*注册
        Button reg = findViewById(R.id.register_merchant_Btn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Drawable drawable = ImgText.getDrawable();//获取当前标签的图片
                String name = NameText.getText().toString();
                String pwd = PwdText.getText().toString();
                String phoneStr = PhoneText.getText().toString();
                String loc = LocSpinner.getSelectedItem().toString();
                String add = AddText.getText().toString();
                String des = DesText.getText().toString();

                if (drawable instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();//获取这个图片的二进制文件
                    Bitmap bitmapDef = ((BitmapDrawable) defaultDrawable).getBitmap();//获取默认头像的二进制文件
                    if (bitmap.sameAs(bitmapDef)) {//判断是否更改默认头像
                        Toast.makeText(RegisterMerchantActivity.this, "请点击图片更换头像", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (phoneStr.isEmpty() || phoneStr.length() != 11) {
                    Toast.makeText(RegisterMerchantActivity.this, "请输入11位有效的电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (name.isEmpty()) {
                    Toast.makeText(RegisterMerchantActivity.this, "请输入店铺名称", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwd.isEmpty()) {
                    Toast.makeText(RegisterMerchantActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (loc.equals("请选择店铺所在地")) {
                    Toast.makeText(RegisterMerchantActivity.this, "请选择所在省/市/区", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (add.isEmpty()) {
                    Toast.makeText(RegisterMerchantActivity.this, "请输入店铺详细地址", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (des.isEmpty()) {
                    Toast.makeText(RegisterMerchantActivity.this, "输入店铺描述，才能吸引到客人哦~", Toast.LENGTH_SHORT).show();
                    return;
                }


                //得到一个图片名字 root/图片文件+"/a.png
                String path= FileImgUntil.getImgName();//获取一个存储图片的路径名字
                FileImgUntil.saveImageBitmapToFileImg(uri,RegisterMerchantActivity.this,path);//保存图片

                long phone = Long.parseLong(phoneStr);
                int a = MerchantDao.saveMerchantInfo(phone, name, pwd, loc, add, des, path);
                if (a == 1) {
                    Toast.makeText(RegisterMerchantActivity.this, "注册商家账号成功", Toast.LENGTH_SHORT).show();
                    //启动登陆页面
                    Intent intent=new Intent(RegisterMerchantActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();//结束当前活动
                } else if (a == -1) {
                    Toast.makeText(RegisterMerchantActivity.this, "该手机号已被商家注册", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterMerchantActivity.this, "注册商家账号失败", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    /**
     * 打开文件选择器
     * @param v
     */
    private void openGallery(View v){
        getContentLauncher.launch("image/*");
    }
}
