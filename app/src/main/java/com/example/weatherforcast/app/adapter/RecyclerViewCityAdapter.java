package com.example.weatherforcast.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherforcast.app.view.Main2Activity;
import com.example.weatherforcast.app.view.MainActivity;
import com.example.weatherforcast.R;
import com.example.weatherforcast.data.model.City;
import com.example.weatherforcast.data.model.current.CurrentWeather;
import com.github.pwittchen.weathericonview.WeatherIconView;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerViewCityAdapter extends RecyclerView.Adapter<RecyclerViewCityAdapter.ViewHolder> {
    private Context context;
    private List<City> cityList;

    public RecyclerViewCityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_city,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Main2Activity.class);
                intent.putExtra(MainActivity.EXTRA_CITY_NAME_API,cityList.get(viewHolder.getAdapterPosition()).getNameApi());
                intent.putExtra(MainActivity.EXTRA_CITY_NAME,cityList.get(viewHolder.getAdapterPosition()).getName());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = cityList.get(position);
        CurrentWeather currentWeather = city.getCurrentWeather();
        if(currentWeather != null){
            DecimalFormat df = new DecimalFormat("#.0");
            holder.textViewCityName.setText(city.getName());
            holder.textViewWindSpeed.setText(" " + currentWeather.getWindSpeed()+" km/h");
            holder.textViewHumidity.setText(df.format((currentWeather.getHumidity()*100)) + " % |");
            holder.textViewTemp.setText(df.format((currentWeather.getTemperature()-32) * 5 /9)+ "°C");
            holder.textViewSummary.setText(currentWeather.getSummary()+"");
            holder.weatherIconView.setIconSize(35);
            holder.weatherIconView.setIconColor(Color.WHITE);
            holder.weatherIconView.setIconResource(context.getString(currentWeather.getIcon()));
        }

    }

    @Override
    public int getItemCount() {
        if(cityList == null) return 0;
        return cityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewCityName,textViewSummary,textViewTemp,textViewHumidity,textViewWindSpeed;
        private WeatherIconView weatherIconView;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewCityName = itemView.findViewById(R.id.tvCityName);
            textViewSummary = itemView.findViewById(R.id.tvSummary);
            textViewTemp = itemView.findViewById( R.id.tvTemperature);
            textViewHumidity = itemView.findViewById(R.id.tvHumidity);
            textViewWindSpeed = itemView.findViewById(R.id.tvWindSpeed);
            weatherIconView = itemView.findViewById(R.id.weatherIconView);
        }
    }
}
