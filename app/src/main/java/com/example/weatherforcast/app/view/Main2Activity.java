package com.example.weatherforcast.app.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherforcast.R;
import com.example.weatherforcast.app.adapter.DayAdapter;
import com.example.weatherforcast.app.adapter.DayCallBack;
import com.example.weatherforcast.data.model.week.Forecastday;
import com.example.weatherforcast.data.model.week.ResponseWeather;
import com.example.weatherforcast.data.rest.RestCallBack;
import com.example.weatherforcast.data.rest.WeatherApi;
import com.example.weatherforcast.data.rest.WeatherFullApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Main2Activity extends AppCompatActivity implements DayCallBack {
    private TextView tvThanhPho, tvNhietDo, tvNhietDoMax,
            tvNhietDoMin, tvNgay,tvGioMax, tvGioMin, tvMtMoc,tvMtLan,tvTime;
    private RecyclerView listDay;
    private ImageView imgTT;
    private DayAdapter dayAapter;
    private List<Forecastday> forecastdays = new ArrayList<>();
    private String cityNameApi,cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        cityNameApi = intent.getStringExtra(MainActivity.EXTRA_CITY_NAME_API);
        cityName = intent.getStringExtra(MainActivity.EXTRA_CITY_NAME);
        initView();
        loadData();
        dayAapter.setCallBack(this);

    }

    private void initView() {

        tvThanhPho = findViewById(R.id.tvThanhPho);
        tvNhietDo = findViewById(R.id.tvNhietDo);
        tvGioMax =findViewById(R.id.tvGioMax);
        tvGioMin = findViewById(R.id.tvGioMin);
        tvMtLan = findViewById(R.id.tvMtLan);
        tvMtMoc = findViewById(R.id.tvMtToc);
        tvNgay = findViewById(R.id.tvNgay);
        tvNhietDoMax = findViewById(R.id.tvNhietDoMax);
        tvNhietDoMin = findViewById(R.id.tvNhietDoMin);
        listDay = findViewById(R.id.rvDay);
        imgTT = findViewById(R.id.imgThoiTiet);
        tvTime = findViewById(R.id.tvTime);
        LinearLayoutManager manager = new LinearLayoutManager(this);;
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listDay.setLayoutManager(manager);
        dayAapter =new DayAdapter(forecastdays,this);
        listDay.setAdapter(dayAapter);

    }

    private void loadData() {
        WeatherApi weatherApi = WeatherFullApi.getInstance().getApiWeek();
        Call<ResponseWeather> weatherCall = weatherApi.getResponseCity("fe9b788e636d4693b1b15436180911",cityNameApi,16);
        RestCallBack.ResponseListener listener = new RestCallBack.ResponseListener() {
            @Override
            public void onSuccess(Object objT2) {
                ResponseWeather responseCity = (ResponseWeather) objT2;
                layDl(responseCity);
            }
        };

        RestCallBack<ResponseWeather,ResponseWeather> restCallBack = new RestCallBack<ResponseWeather, ResponseWeather>(listener) {
            @Override
            public ResponseWeather getData(ResponseWeather objT1) {
                return objT1;
            }
        };
        weatherCall.enqueue(restCallBack);
    }

    private void layDl(ResponseWeather responseWeather) {
        Glide.with(this).load("http:"+responseWeather.getCurrent().getCondition().getIcon()).into(imgTT);
        String time [] = responseWeather.getLocation().getLocaltime().trim().split(" ");
        tvTime.setText(time[1]);
        tvThanhPho.setText(cityName);
        tvNhietDo.setText(responseWeather.getCurrent().getTempC()+" °C");
        tvNgay.setText(fomatDate(time[0]));
        tvNhietDoMax.setText("Nhiệt độ max: \n"+responseWeather.getForecast().getForecastday().get(0).getDay().getMaxtempC()+" °C");
        tvNhietDoMin.setText("Nhiệt độ min: \n"+responseWeather.getForecast().getForecastday().get(0).getDay().getMintempC()+" °C");
        tvGioMin.setText("Sức gió: \n"+responseWeather.getForecast().getForecastday().get(0).getDay().getMaxwindMph()+"m/s");
        tvGioMax.setText("Độ ẩm: \n"+responseWeather.getForecast().getForecastday().get(0).getDay().getAvghumidity()+"%");
        tvMtMoc.setText("Mặt trời mọc: \n"+responseWeather.getForecast().getForecastday().get(0).getAstro().getSunrise());
        tvMtLan.setText("Mặt trời lặn: \n"+responseWeather.getForecast().getForecastday().get(0).getAstro().getSunset());
        forecastdays.clear();
        forecastdays.addAll(responseWeather.getForecast().getForecastday());
        dayAapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {

        Forecastday forecastday = forecastdays.get(position);

        Glide.with(this).load("http:"+forecastday.getDay().getCondition().getIcon()).into(imgTT);
        tvNhietDo.setText(forecastday.getDay().getAvgtempC()+"");
        tvNgay.setText(fomatDate(forecastday.getDate().trim()));
        tvNhietDoMax.setText("Nhiệt độ max: \n"+forecastday.getDay().getMaxtempC()+" C");
        tvNhietDoMin.setText("Nhiệt độ min: \n"+forecastday.getDay().getMintempC()+" C");
        tvGioMin.setText("Sức gió: \n"+forecastday.getDay().getMaxwindMph()+"m/s");
        tvGioMax.setText("Độ ẩm: \n"+forecastday.getDay().getAvghumidity()+"%");
        tvMtMoc.setText("Mặt trời mọc: \n"+forecastday.getAstro().getSunrise());
        tvMtLan.setText("Mặt trời lặn: \n"+forecastday.getAstro().getSunset());
    }
    public static String fomatDate(String date){
        String dateFormat = "";
        String [] tam = date.split("-");
        for (int i = tam.length-1; i>=0 ; i--) {
            dateFormat+=tam[i]+"-";
        }
        dateFormat = dateFormat.substring(0,dateFormat.length()-1);
        return dateFormat;
    }
}
