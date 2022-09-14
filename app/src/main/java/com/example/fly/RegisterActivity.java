package com.example.fly;

import static com.example.fly.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fly.R;
import com.example.fly.RegisterModel.Register;
import com.example.fly.model.login;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    Button button;
    EditText editTextU;
    EditText editTextP;
    String username;
    String password;
    com.example.fly.RegisterModel.Register register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextU = findViewById(R.id.username);
        editTextP = findViewById(R.id.password);
        button = findViewById(Register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editTextU.getText().toString();
                password = editTextP.getText().toString();
                if (password.length() < 8){
                    Toast.makeText(RegisterActivity.this, "密码长度不能小于8", Toast.LENGTH_SHORT).show();
                }else {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    FormBody.Builder formBody = new FormBody.Builder();
                    formBody.add("username",username);
                    formBody.add("password",password);
                    Request request = new Request.Builder()
                            .url("https://mock.w2code.com/api/register")
                            .post(formBody.build())
                            .build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.d("res","网络出错"+e);
                            Looper.prepare();
                            Toast.makeText(RegisterActivity.this,"网络出错",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String res = response.body().string();
                            Gson gson = new Gson();
                            register = gson.fromJson(res, Register.class);
                            if (register.getData().getVerifySuccess()){
                                Looper.prepare();
                                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }else{
                                Looper.prepare();
                                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    });
                }
            }
        });

    }
}