package com.example.weatherforcast.data.rest;

public class WeatherFullApi {
    private static WeatherFullApi instance;
    private WeatherApi apiCurrently,apiWeek;

    private WeatherFullApi(){
        apiCurrently = RetrofitFactory.getRetrofit(WeatherApi.BASE_URL_CURRENTLY).create(WeatherApi.class);
        apiWeek = RetrofitFactory.getRetrofit(WeatherApi.BASE_URL_WEEK).create(WeatherApi.class);
    }

    public static WeatherFullApi getInstance() {
        if(instance == null){
            instance = new WeatherFullApi();
        }
        return instance;
    }

    public WeatherApi getApiCurrently(){
        if(apiCurrently == null){
            new WeatherFullApi();
        }
        return apiCurrently;
    }

    public WeatherApi getApiWeek(){
        if(apiWeek == null){
            new WeatherFullApi();
        }
        return apiWeek;
    }
}
