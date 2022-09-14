package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ImageView imageView;
    EditText editText;
    EditText editTextC;
    EditText editTextM;
    Button buttonG;
    String departure;
    String destination;
    String date;
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        imageView = findViewById(R.id.image);
        editText = findViewById(R.id.rqi);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(MainActivity.this, 6, editText, calendar);
            }
        });
        intent();
        editTextC = findViewById(R.id.edit_queryC);
        editTextM = findViewById(R.id.edit_queryM);
        buttonG = findViewById(R.id.Go);
        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departure = editTextC.getText().toString();
                destination = editTextM.getText().toString();
                date = editText.getText().toString();
                Log.d("TAG", "onClick: "+departure);
                Log.d("TAG", "onClick: "+destination);
                Log.d("TAG", "onClick: "+date);
                if (!departure.equals("") && !destination.equals("") && !date.equals("")){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, ListActivity.class);
                    intent.putExtra("departure", departure);
                    intent.putExtra("destination", destination);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "请补充完整信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void intent() {
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");
        System.out.println(username);
        System.out.println(password);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();//初始化
        editor.putString("username",username);//写入数据
        editor.putString("password",password);
        editor.commit();//提交
    }
    public static void showDatePickerDialog(Activity activity, int themeResId, final EditText tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        DatePickerDialog dialog = new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.black));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setGravity(Gravity.RIGHT);

    }
}