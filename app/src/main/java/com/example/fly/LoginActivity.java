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
    SharedPreferences.Editor editor;

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
        OkHttpClient client = new OkHttpClient();//??????OkHttpClient?????????
        FormBody.Builder formBody = new FormBody.Builder();//?????????????????????
        formBody.add("username",username);//?????????????????????
        formBody.add("password",password);
        Request request = new Request.Builder()//??????Request ?????????
                .url("https://mock.w2code.com/api/login")
                .post(formBody.build())//???????????????
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(LoginActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LoginActivity.super.onRestart();
                String user=response.body().string();
                Gson gson = new Gson();
                login = gson.fromJson(user, login.class);
                if(login.getData().getVerifySuccess()){//????????????????????????????????????
                    sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token",login.getData().getUserInfo().getToken().toString());
                    editor.commit();//????????????
                    //????????????
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this,MainActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    //??????????????????
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, login.getData().getUserInfo().getUsername()+"???????????????", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }else{
                    editTextP.post(new Runnable() {
                        @Override
                        public void run() {
                            editTextP.setText("");
                        }
                    });
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });//????????????????????????get?????????????????????????????????
    }
    public void checkBoxYES(){
//        Toast.makeText(LoginActivity.this,"checkBoxYES",Toast.LENGTH_SHORT).show();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();//?????????
        String username = editTextU.getText().toString();
        String password = editTextP.getText().toString();
        editor.putString("username",username);//????????????
        editor.putString("password",password);
        editor.commit();//??????
    }
    public void checkBoxYES1(){
//        Toast.makeText(LoginActivity.this,"checkBoxYES1",Toast.LENGTH_SHORT).show();
        username = sharedPreferences.getString("username","");
        password = sharedPreferences.getString("password","");
        editTextU.setText(username);
        editTextP.setText(password);
    }
}