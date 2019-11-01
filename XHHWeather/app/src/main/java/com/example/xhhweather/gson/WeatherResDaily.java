package com.example.xhhweather.gson;


public class WeatherResDaily {
    private String date;
    private String text_day;
    private String code_day;
    private String text_night;
    private String code_night;
    private String high;
    private String low;
    private String precip;
    private String wind_direction;
    private String wind_direction_degree;
    private String wind_speed;
    private String wind_scale;

    public String getDate() {
        return date;
    }

    public String getText_day() {
        return text_day;
    }

    public String getCode_day() {
        return code_day;
    }

    public String getText_night() {
        return text_night;
    }

    public String getCode_night() {
        return code_night;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getPrecip() {
        return precip;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public String getWind_direction_degree() {
        return wind_direction_degree;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getWind_scale() {
        return wind_scale;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText_day(String text_day) {
        this.text_day = text_day;
    }

    public void setCode_day(String code_day) {
        this.code_day = code_day;
    }

    public void setText_night(String text_night) {
        this.text_night = text_night;
    }

    public void setCode_night(String code_night) {
        this.code_night = code_night;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public void setWind_direction_degree(String wind_direction_degree) {
        this.wind_direction_degree = wind_direction_degree;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setWind_scale(String wind_scale) {
        this.wind_scale = wind_scale;
    }

    @Override
    public String toString() {
        return "WeatherResDaily{" +
                "date='" + date + '\'' +
                ", text_day='" + text_day + '\'' +
                ", code_day='" + code_day + '\'' +
                ", text_night='" + text_night + '\'' +
                ", code_night='" + code_night + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", precip='" + precip + '\'' +
                ", wind_direction='" + wind_direction + '\'' +
                ", wind_direction_degree='" + wind_direction_degree + '\'' +
                ", wind_speed='" + wind_speed + '\'' +
                ", wind_scale='" + wind_scale + '\'' +
                '}';
    }
}
