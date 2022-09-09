package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextU;
    EditText editTextP;
    Button button;
    String username;
    String password;
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
                username = editTextU.getText().toString();
                password = editTextP.getText().toString();
                Toast.makeText(LoginActivity.this, "username:"+username+"password:"+password, Toast.LENGTH_SHORT).show();
            }
        });
    }
}