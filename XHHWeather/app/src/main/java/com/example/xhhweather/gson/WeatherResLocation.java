package com.example.xhhweather.gson;


public class WeatherResLocation {
    private String id;
    private String name;
    private String country;
    private String path;
    private String timezone;
    private String timezone_offset;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPath() {
        return path;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getTimezone_offset() {
        return timezone_offset;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTimezone_offset(String timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    @Override
    public String toString() {
        return "WeatherResLocation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", path='" + path + '\'' +
                ", timezone='" + timezone + '\'' +
                ", timezone_offset='" + timezone_offset + '\'' +
                '}';
    }
}
