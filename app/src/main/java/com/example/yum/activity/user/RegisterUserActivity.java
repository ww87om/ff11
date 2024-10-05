package com.example.yum.activity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.yum.R;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // 获取 Spinner
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

        // 设置下拉菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 设置适配器到 Spinner
        userAddressSpinner.setAdapter(adapter);
    }
}
