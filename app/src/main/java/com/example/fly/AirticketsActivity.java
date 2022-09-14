package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

public class AirticketsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airtickets);

        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_airtickets);//R.layout.main为你的布局文件
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.activity_airtickets);//R.layout.title为你删除标题后的布局文件
    }
}