package com.example.xhhweather;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SelectCity extends AppCompatActivity{
    //ListView使用
    private ListView listView;
    private List<CityItem> list = new ArrayList<>();
    private CityAdapter cityAdapter;

    //返回按钮
    private Button btnBackMain;
    //添加按钮
    private Button btnAddCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        listView = findViewById(R.id.lv_select);
        btnBackMain = findViewById(R.id.btn_back);
        btnAddCity = findViewById(R.id.btn_add);

        initCity();

        cityAdapter = new CityAdapter(list);
        listView.setAdapter(cityAdapter);

        backMainWeather();
        addCity();

        //选择点击城市
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //final String requestBingPic="http://guolin.tech/api/bing_pic";
                CityItem city=list.get(i);
                //Toast.makeText(SelectCity.this,city.getCityPinYin(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();

                intent.putExtra("CITYPINYIN",city.getCityPinYin());
                setResult(1002,intent);
                finish();
            }
        });
    }

    //ListView使用
    public void initCity(){
        CityItem guangzhou = new CityItem("广州","guangzhou");
        list.add(guangzhou);
    }
    //返回按钮
    public void backMainWeather(){
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectCity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //添加城市
    public void addCity(){
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] Country = {"广州", "深圳", "上海", "北京"};
                final String[] CountryPinYin = {"guangzhou", "shenzhen", "shanghai", "beijing"};
                final boolean[] booleans = {false, false, false, false};
                AlertDialog.Builder dialog = new AlertDialog.Builder(SelectCity.this);
                dialog.setTitle("对不起,您只能选择以下四个城市");
                dialog.setSingleChoiceItems(Country, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < Country.length; j++) {
                            if (Country[j].equals(Country[i])) {
                                booleans[i] = true;
                            }
                        }
                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    int CountryNameInt;
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < booleans.length; j++) {
                            if (booleans[j]) {
                                CountryNameInt = j;
                                break;
                                //Toast.makeText(AddCountryActivity.this,CountryName,Toast.LENGTH_LONG).show();
                            }
                        }
                        //初始化城市
                        CityItem cityItem=null;
                        //判断哪个城市选中
                        for(int k=0;k<booleans.length;k++){
                            if(k==CountryNameInt){
                                Toast.makeText(SelectCity.this, Country[CountryNameInt], Toast.LENGTH_LONG).show();
                                cityItem=new CityItem(Country[CountryNameInt],CountryPinYin[CountryNameInt]);
                                list.add(cityItem);
                                cityAdapter.notifyDataSetChanged();
                                break;
                            }
                        }
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.create();
                dialog.show();
            }
        });
    }
}
