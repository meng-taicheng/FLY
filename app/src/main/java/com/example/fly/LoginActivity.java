package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fly.model.login;
import com.google.gson.Gson;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextU = findViewById(R.id.username);
        editTextP = findViewById(R.id.password);
        button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String username = editTextU.getText().toString();
                final String password = editTextP.getText().toString();
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
                Log.e("onFailure:","失败了"+e);
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                editTextU.setText("");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Gson gson = new Gson();
//                login login = gson.fromJson(String.valueOf(response), login.class);
                String user=response.body().string();
//                Toast.makeText(LoginActivity.this, "user:"+user, Toast.LENGTH_SHORT).show();
                Log.d("onFailure:","成功了:"+user);
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
                    Log.d("kwwl","获取数据成功了");
                    Log.d("kwwl","response.code()=="+response.code());
                    Log.d("kwwl","response.body().string()=="+response.headers());
                }else{
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                }
            }
        });//回调方法的使用与get异步请求相同，此时略。
    }

}