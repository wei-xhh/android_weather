package com.example.xhhweather.gson;

import java.util.List;

public class Weather {
    private List<WeatherResults> results;

    public List<WeatherResults> getResults() {
        return results;
    }

    public void setResults(List<WeatherResults> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "results=" + results +
                '}';
    }
}
