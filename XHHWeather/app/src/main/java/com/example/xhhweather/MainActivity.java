package com.example.xhhweather;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xhhweather.gson.Weather;
import com.example.xhhweather.gson.WeatherResults;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{
    private Button btnSelectCity;
    private TextView weatherTitleCityname;
    private TextView weatherTitleCitypinyin;

    private TextView tvFirstDayDate;
    private TextView tvFirstDayPhe;
    private TextView tvFirstDayHigh;
    private TextView tvFirstDayLow;

    private TextView tvSecondDayDate;
    private TextView tvSecondDayPhe;
    private TextView tvSecondDayHigh;
    private TextView tvSecondDayLow;

    private TextView tvThirdDayDate;
    private TextView tvThirdDayPhe;
    private TextView tvThirdDayHigh;
    private TextView tvThirdDayLow;

    private TextView temperatureText;
    private TextView caseText;

    private ServiceConnection connection;

    private SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initfindViewById();

        Intent intent=new Intent();
        intent.setClass(MainActivity.this,UpdateService.class);
        //startService(intent);

        btnSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SelectCity.class);
                startActivityForResult(intent,1001);
            }
        });

        SharedPreferences prefs=getSharedPreferences("weather",MODE_PRIVATE);
        String weatherString=prefs.getString("WEATHER",null);
        boolean isSelect=prefs.getBoolean("isSelected",false);

        if(isSelect && weatherString!=null){
            Weather weatherInfo= Utility.handleWeatherRsponse(weatherString);
            showWeatherInfo(weatherInfo);

        }else{
            initDefaultCity();
        }

        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this,"刷新了",Toast.LENGTH_SHORT).show();
                String cityPinYin= (String) weatherTitleCitypinyin.getText();
                //Log.e("MainActivity","返回城市的拼音"+cityPinYin);
                //心知天气的api
                Log.e("MainActivity","城市拼音"+cityPinYin);

                GetrequestWeather(cityPinYin);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 初始化
     */
    private void initfindViewById(){
        btnSelectCity=findViewById(R.id.manage_city_btn);
        weatherTitleCityname=findViewById(R.id.weather_title_cityname);
        weatherTitleCitypinyin=findViewById(R.id.weather_title_citypinyin);


        temperatureText=findViewById(R.id.temperature_text);
        caseText=findViewById(R.id.case_text);

        tvFirstDayDate=findViewById(R.id.tv_firstday_date);
        tvFirstDayPhe=findViewById(R.id.tv_firstday_phe);
        tvFirstDayHigh=findViewById(R.id.tv_firstday_high);
        tvFirstDayLow=findViewById(R.id.tv_firstday_low);

        tvSecondDayDate=findViewById(R.id.tv_secondday_date);
        tvSecondDayPhe=findViewById(R.id.tv_secondday_phe);
        tvSecondDayHigh=findViewById(R.id.tv_secondday_high);
        tvSecondDayLow=findViewById(R.id.tv_secondday_low);

        tvThirdDayDate=findViewById(R.id.tv_thirdday_date);
        tvThirdDayPhe=findViewById(R.id.tv_thirdday_phe);
        tvThirdDayHigh=findViewById(R.id.tv_thirdday_high);
        tvThirdDayLow=findViewById(R.id.tv_thirdday_low);

        refreshLayout=findViewById(R.id.swipe_refresh);
    }
    /**
     * 得到返回结果
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001 && resultCode==1002){
            String cityPinYin=data.getStringExtra("CITYPINYIN");
            weatherTitleCitypinyin.setText(cityPinYin);
            //Log.e("MainActivity","返回城市的拼音"+cityPinYin);
            //心知天气的api
            GetrequestWeather(cityPinYin);
        }
    }

    /**
     * 获得请求后的天气数据，后显示showWeatherInfo（）
     * @param cityPinYin
     */
    public void GetrequestWeather(String cityPinYin){
        String url="https://api.seniverse.com/v3/weather/daily.json?key=SojsF4VNkV_cTQexq&location=" + cityPinYin + "&language=zh-Hans&unit=c&start=0&days=5";
        final Utility util=new Utility();
        util.showProgressDialog(MainActivity.this);
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String weatherData=response.body().string();
                Log.e("MainActivity","得到的数据"+weatherData);
                final Weather weatherInfo= Utility.handleWeatherRsponse(weatherData);
                if(weatherInfo!=null){
                    SharedPreferences.Editor editor= getSharedPreferences("weather",MODE_PRIVATE).edit();
                    editor.putString("WEATHER", weatherData);
                    editor.putBoolean("isSelected",true);
                    editor.apply();
                }
                util.dismiss();
                showWeatherInfo(weatherInfo);
            }
        });
    }

    /**
     * 显示天气信息
     * @param weatherInfo
     */
    private void showWeatherInfo(final Weather weatherInfo){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WeatherResults results=weatherInfo.getResults().get(0);
                String cityName=results.getLocation().getName();//城市名称
                weatherTitleCityname.setText(cityName);

                //将3天内的天气数据获得后存入二维数组
                String [] [] resultDaily=new String[3][5];
                for(int i=0;i<3;i++){
                    for(int j=0;j<4;){
                        resultDaily[i][j]=results.getDaily().get(i).getDate();j++;
                        resultDaily[i][j]=results.getDaily().get(i).getText_day();j++;
                        resultDaily[i][j]=results.getDaily().get(i).getHigh();j++;
                        resultDaily[i][j]=results.getDaily().get(i).getLow();j++;
                    }
                }
                temperatureText.setText(resultDaily[0][2]+"℃");
                caseText.setText(resultDaily[0][1]);

                for(int j=0;j<4;){
                    //Log.e("resultDaily",resultDaily[0][j]);
                    tvFirstDayDate.setText(resultDaily[0][j]);j++;
                    tvFirstDayPhe.setText(resultDaily[0][j]);j++;
                    tvFirstDayHigh.setText(resultDaily[0][j]);j++;
                    tvFirstDayLow.setText(resultDaily[0][j]);j++;
                }
                for(int j=0;j<4;){
                    tvSecondDayDate.setText(resultDaily[1][j]);j++;
                    tvSecondDayPhe.setText(resultDaily[1][j]);j++;
                    tvSecondDayHigh.setText(resultDaily[1][j]);j++;
                    tvSecondDayLow.setText(resultDaily[1][j]);j++;
                }
                for(int j=0;j<4;){
                    tvThirdDayDate.setText(resultDaily[2][j]);j++;
                    tvThirdDayPhe.setText(resultDaily[2][j]);j++;
                    tvThirdDayHigh.setText(resultDaily[2][j]);j++;
                    tvThirdDayLow.setText(resultDaily[2][j]);j++;
                }
            }
        });
    }

    /**
     * 默认城市信息
     */
    public void initDefaultCity(){
        weatherTitleCitypinyin.setText("guangzhou");
        GetrequestWeather("guangzhou");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
