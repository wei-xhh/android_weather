package com.example.xhhweather;

import com.example.xhhweather.gson.Weather;
import com.google.gson.Gson;

public class Utility {
    /**
     * 将放回的json数据解析成Weather 实体类
     */
    public static Weather handleWeatherRsponse(String response){
        Gson gson =new Gson();
        Weather weatherInfo=gson.fromJson(response,Weather.class);
        return weatherInfo;
    }
}
