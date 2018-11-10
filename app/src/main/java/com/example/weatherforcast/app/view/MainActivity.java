package com.example.weatherforcast.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.weatherforcast.R;
import com.example.weatherforcast.app.adapter.RecyclerViewCityAdapter;
import com.example.weatherforcast.data.model.City;
import com.example.weatherforcast.data.model.current.CurrentWeather;
import com.example.weatherforcast.data.model.current.WeatherForcast;
import com.example.weatherforcast.data.rest.RestCallBack;
import com.example.weatherforcast.data.rest.WeatherApi;
import com.example.weatherforcast.data.rest.WeatherFullApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_CITY_NAME = "com.example.weatherforcast.extraCityName";
    public static final String EXTRA_CITY_NAME_API = "com.example.weatherforcast.extraCityNameApi";

    private List<City> cityList;
    private RecyclerView recyclerView;
    private RecyclerViewCityAdapter recyclerViewCityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = new ArrayList<>();
        cityList.add(new City("Hà Nội","Hanoi",21.006167, 105.826469));
        cityList.add(new City("Đà Nẵng","Danang",16.063021, 108.220300));
        cityList.add(new City("TP. Hồ Chí Minh","Saigon",10.788155, 106.643227));

        setRecyclerView();

        WeatherApi weatherApi = WeatherFullApi.getInstance().getApiCurrently();
        for(final City city : cityList){
            Call<WeatherForcast> jsonCall = weatherApi.getWeatherForcast(city.getLatitude() +"",city.getLongtitude() + "");
            RestCallBack.ResponseListener<CurrentWeather> listener = new RestCallBack.ResponseListener<CurrentWeather>() {
                @Override
                public void onSuccess(CurrentWeather objT2) {
                    city.setCurrentWeather(objT2);
                    recyclerViewCityAdapter.notifyDataSetChanged();
                }
            };

            RestCallBack<WeatherForcast,CurrentWeather> restCallBack = new RestCallBack<WeatherForcast, CurrentWeather>(listener) {
                @Override
                public CurrentWeather getData(WeatherForcast objT1) {
                    return objT1.getCurrentWeather();
                }
            };
            jsonCall.enqueue(restCallBack);
        }

    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewCity);
        recyclerViewCityAdapter = new RecyclerViewCityAdapter(this,cityList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewCityAdapter);
    }
}
