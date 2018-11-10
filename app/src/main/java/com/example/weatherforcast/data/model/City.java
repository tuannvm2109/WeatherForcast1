package com.example.weatherforcast.data.model;

import com.example.weatherforcast.data.model.current.CurrentWeather;

import java.io.Serializable;

public class City implements Serializable {
    private String name;
    private String nameApi;
    private double latitude;
    private double longtitude;
    private CurrentWeather currentWeather;

    public City(String name, String nameApi, double latitude, double longtitude) {
        this.name = name;
        this.nameApi = nameApi;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameApi() {
        return nameApi;
    }

    public void setNameApi(String nameApi) {
        this.nameApi = nameApi;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }
}
