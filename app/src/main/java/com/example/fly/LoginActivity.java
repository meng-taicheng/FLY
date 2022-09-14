package com.example.fly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fly.model.Userpassword;
import com.example.fly.model.login;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editTextU;
    EditText editTextP;
    Button button;
    Button button1;
    login login;
    CheckBox checkBox;
    String username;
    String password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextU = findViewById(R.id.username);
        editTextP = findViewById(R.id.password);
        button1 = findViewById(R.id.Register);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        checkBox = findViewById(R.id.chain);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = sharedPreferences.getString("username","");
        password = sharedPreferences.getString("password","");
        editTextU.setText(username);
        editTextP.setText(password);
        button = findViewById(R.id.login);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){
                    checkBoxYES();
                }
            }
        });
        checkBoxYES1();
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                username = editTextU.getText().toString();
                password = editTextP.getText().toString();
                postDataWithParame(username,password);
            }
        });
    }

    private void postDataWithParame(String username,String password) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("username",username);//传递键值对参数
        formBody.add("password",password);
        Request request = new Request.Builder()//创建Request 对象。
                .url("https://mock.w2code.com/api/login")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LoginActivity.super.onRestart();
                String user=response.body().string();
                Gson gson = new Gson();
                login = gson.fromJson(user, login.class);
                if(login.getData().getVerifySuccess()){//回调的方法执行在子线程。
                    //页面跳转
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this,MainActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    //登陆成功提示
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, login.getData().getUserInfo().getUsername()+"，欢迎登录", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }else{
                    editTextP.post(new Runnable() {
                        @Override
                        public void run() {
                            editTextP.setText("");
                        }
                    });
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });//回调方法的使用与get异步请求相同，此时略。
    }
    public void checkBoxYES(){
//        Toast.makeText(LoginActivity.this,"checkBoxYES",Toast.LENGTH_SHORT).show();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();//初始化
        String username = editTextU.getText().toString();
        String password = editTextP.getText().toString();
        editor.putString("username",username);//写入数据
        editor.putString("password",password);
        editor.commit();//提交
    }
    public void checkBoxYES1(){
//        Toast.makeText(LoginActivity.this,"checkBoxYES1",Toast.LENGTH_SHORT).show();
        username = sharedPreferences.getString("username","");
        password = sharedPreferences.getString("password","");
        editTextU.setText(username);
        editTextP.setText(password);
    }
}