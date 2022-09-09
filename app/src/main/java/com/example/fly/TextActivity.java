package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fly.model.data;
import com.google.gson.Gson;

public class TextActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        button = findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String departmentJson = "{'id' : 1, "
                        + "'name': 'HR',"
                        + "'users' : ["
                        + "{'name': 'Alex','id': 1}, "
                        + "{'name': 'Brian','id':2}, "
                        + "{'name': 'Charles','id': 3}]}";

                Gson gson = new Gson();

                data data = gson.fromJson(departmentJson, data.class);
//                System.out.println(data.getUsers().get(1).getName());
            }
        });
    }
}