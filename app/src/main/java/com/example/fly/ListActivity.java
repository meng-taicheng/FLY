package com.example.fly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fly.ListModel.dataList;
import com.example.fly.ListModel.List;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends AppCompatActivity {

    TextView textViewR;
    TextView textViewC;
    TextView textViewM;
    String departure;
    String destination;
    String date;
    TableRow tableRow;
    ArrayList data = new  ArrayList();
    ListView listView;
    com.example.fly.model.userInfo userInfo;
    com.example.fly.ListModel.List List;
    com.example.fly.ListModel.dataList dataList;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();
        Intent intent =getIntent();
        departure = intent.getStringExtra("departure");
        destination = intent.getStringExtra("destination");
        date = intent.getStringExtra("date");
        textViewR = findViewById(R.id.rqi);
        textViewR.setText("日期："+date);
        textViewC = findViewById(R.id.chu);
        textViewC.setText(departure);
        textViewM = findViewById(R.id.mu);
        textViewM.setText(destination);
        tableRow = findViewById(R.id.data);

        SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        Log.d("token:",token);
        Log.d("from:",departure);
        Log.d("to:",destination);
        Log.d("time:",date);
//        获取到集合数据
//        名字列表，之后可以动态加入数据即可，这里只是数据例子
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("token",token);
        formBody.add("from",departure);
        formBody.add("to",destination);
        formBody.add("time",date);
        Request request = new Request.Builder()//创建request对象
                .url("https://mock.w2code.com/api/ticket")
                .post(formBody.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Looper.prepare();
                Toast.makeText(ListActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String res = response.body().string();
//                Object obj = JSONObject.parse(res);
                Log.d("response",res);
                Gson gson = new Gson();
//                List appList = gson.fromJson(res, new TypeToken<List>() {}.getType());

                list = gson.fromJson(res, List.class);
//                Log.d("List:", String.valueOf(list.getData().isVerifySuccess()));
                if (list.getData().isVerifySuccess()){
                    Log.d("isVerifySuccess","获取成功");
//                    Log.d("getDataLists",""+list.getData().getUserInfo().getDataLists().get(0).getName());
//                    for (int i=0;i<List.getData().getDataLists().size();i++){
//                        Log.d("i",""+i);
//                        listName.add(List.getData().getUserInfo().getDataLists().get(i).getName());
//                        listTime.add(List.getData().getUserInfo().getDataLists().get(i).getTime());
//                        Starttime.add(List.getData().getUserInfo().getDataLists().get(i).getStarttime());
//                        Endtime.add(List.getData().getUserInfo().getDataLists().get(i).getEndtime());
//                        Number.add(List.getData().getUserInfo().getDataLists().get(i).getNumber());
//                        Lowestprice.add(List.getData().getUserInfo().getDataLists().get(i).getLowestprice());
//                    }

//                    List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
//                    HashMap<String, Object> item = new HashMap<String, Object> ();
//                    for(int i = 0; i < listName.size(); i++){
//                        item.put("time", listTime.get(i));
//                        item.put("name", listName.get(i));
//                        item.put("starttime", Starttime.get(i));
//                        item.put("endtime", Endtime.get(i));
//                        item.put("number", Number.get(i));
//                        item.put("lowestprice", Lowestprice.get(i));
//                        data.add(item);
//                    }
//                    for (Map.Entry<String, Object> entry : item.entrySet()) {
//                        String mapKey = entry.getKey();
//                        Object mapValue = entry.getValue();
//                        System.out.println("Map"+mapKey + "：" + mapValue);
//                    }
//                    创建SimpleAdapter适配器将数据绑定到item显示控件上
//                    SimpleAdapter adapter = new SimpleAdapter(ListActivity.this, data, R.layout.item,
//                            new String[]{"time", "name", "starttime", "endtime", "number", "lowestprice"},
//                            new int[]{R.id.time, R.id.name,R.id.starttime, R.id.endtime,R.id.number, R.id.lowestprice});
//                    实现列表的显示
//                    listView.setAdapter(adapter);
                }else{
                    Log.d("isVerifySuccess","获取失败");
                    Looper.prepare();
                    Toast.makeText(ListActivity.this,"暂无机票",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });

//        listView = findViewById(R.id.list_item);
//        List<dataList> dataLists = new ArrayList<>();
//        for (int i=0; i<=4; i++){
//            dataList dataList = new dataList("2022/9/20","深圳航空","11:35","13:15","ZH7581","720");
//            dataLists.add(dataList);
//            dataList dataList1 = new dataList("2022/9/20","深圳航空","11:35","13:15","ZH7581","720");
//            dataLists.add(dataList);
//            dataList dataList2 = new dataList("2022/9/20","深圳航空","11:35","13:15","ZH7581","720");
//            dataLists.add(dataList);
//            dataList dataList3 = new dataList("2022/9/20","深圳航空","11:35","13:15","ZH7581","720");
//            dataLists.add(dataList);
//            dataList dataList4 = new dataList("2022/9/20","深圳航空","11:35","13:15","ZH7581","720");
//            dataLists.add(dataList);
//            dataList dataList5 = new dataList("2022/9/20","深圳航空","11:35","13:15","ZH7581","720");
//            dataLists.add(dataList);
//        }
//        ListAdapter listAdapter = new ListAdapter(ListActivity.this,R.layout.list,dataLists);
//        listView.setAdapter(listAdapter);
    }

    private void analysisJsonWithJsonObject(Response response){
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}