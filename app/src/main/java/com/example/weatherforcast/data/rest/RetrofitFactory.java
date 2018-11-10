package com.example.weatherforcast.data.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    public static Retrofit getRetrofit(String baseUrl){
        Retrofit retrofit = null;
        switch (baseUrl){
            case WeatherApi.BASE_URL_CURRENTLY:
                retrofit = new Retrofit.Builder()
                        .baseUrl(WeatherApi.BASE_URL_CURRENTLY)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                break;
            case WeatherApi.BASE_URL_WEEK:
                retrofit = new Retrofit.Builder()
                        .baseUrl(WeatherApi.BASE_URL_WEEK)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                break;
        }
        return retrofit;
    }
}
