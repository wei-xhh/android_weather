package com.example.xhhweather.gson;

import java.util.List;


 public class WeatherResults {
     private WeatherResLocation location;
     private List<WeatherResDaily> daily;
     private String last_update;

     public WeatherResLocation getLocation() {
         return location;
     }

     public List<WeatherResDaily> getDaily() {
         return daily;
     }

     public String getLast_update() {
         return last_update;
     }

     public void setLocation(WeatherResLocation location) {
         this.location = location;
     }

     public void setDaily(List<WeatherResDaily> daily) {
         this.daily = daily;
     }

     public void setLast_update(String last_update) {
         this.last_update = last_update;
     }

     @Override
     public String toString() {
         return "WeatherResults{" +
                 "location=" + location +
                 ", daily=" + daily +
                 ", last_update='" + last_update + '\'' +
                 '}';
     }
 }
