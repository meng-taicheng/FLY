package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.fly.R;

public class ListActivity extends AppCompatActivity {

    TextView textViewR;
    TextView textViewC;
    TextView textViewM;
    String departure;
    String destination;
    String date;
    TableRow tableRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();
        Intent intent =getIntent();
        departure = intent.getStringExtra("departure");
        destination = intent.getStringExtra("destination");
        date = intent.getStringExtra("date");
        System.out.println("departure: "+departure);
        System.out.println("destination: "+destination);
        System.out.println("date: "+date);
        textViewR = findViewById(R.id.rqi);
        textViewR.setText("日期："+date);
        textViewC = findViewById(R.id.chu);
        textViewC.setText(departure);
        textViewM = findViewById(R.id.mu);
        textViewM.setText(destination);
        tableRow = findViewById(R.id.data);
    }
}