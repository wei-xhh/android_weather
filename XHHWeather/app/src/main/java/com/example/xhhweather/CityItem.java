package com.example.xhhweather;

public class CityItem {
    private String cityName;
    private String cityPinYin;

    public CityItem(String cityName,String cityPinYin) {
        this.cityName = cityName;
        this.cityPinYin=cityPinYin;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityPinYin() {
        return cityPinYin;
    }
}
