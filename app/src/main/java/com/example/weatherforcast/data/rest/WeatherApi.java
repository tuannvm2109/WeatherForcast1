package com.example.weatherforcast.data.rest;

import com.example.weatherforcast.data.model.current.WeatherForcast;
import com.example.weatherforcast.data.model.week.ResponseWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {
    static final String BASE_URL_CURRENTLY = "https://api.darksky.net/forecast/";
    static final String BASE_URL_WEEK = "http://api.apixu.com/v1//";

    @GET("d319ab0a4143df360d6c978092c5b1d1/{latitude},{longtitude}")
    Call<WeatherForcast> getWeatherForcast(@Path("latitude") String latitude, @Path("longtitude") String longtitude);

    @POST("forecast.json")
    Call<ResponseWeather> getResponseCity(
            @Query("key")String key, @Query("q") String q, @Query("days") int days);
}
