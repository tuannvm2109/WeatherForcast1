package com.example.weatherforcast.data.model.current;

import com.example.weatherforcast.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("temperature")
    @Expose
    private float temperature;

    @SerializedName("humidity")
    @Expose
    private float humidity;

    @SerializedName("visibility")
    @Expose
    private float visibility;

    @SerializedName("uvIndex")
    @Expose
    private float uvIndex;

    @SerializedName("windSpeed")
    @Expose
    private float windSpeed;

    @SerializedName("cloundCover")
    @Expose
    private float cloundCover;

    @SerializedName("pressure")
    @Expose
    private float pressure;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getIcon() {
        switch (icon){
            case "clear-day": return R.string.wi_forecast_io_clear_day;
            case "clear-night": return R.string.wi_forecast_io_clear_night;
            case "rain": return R.string.wi_rain;
            case "snow": return R.string.wi_snow;
            case "sleet": return R.string.wi_sleet;
            case "wind": return R.string.wi_windy;
            case "fog": return R.string.wi_fog;
            case "cloudy": return R.string.wi_cloudy;
            case "partly-cloudy-day": return R.string.wi_forecast_io_partly_cloudy_day;
            case "partly-cloudy-night": return R.string.wi_forecast_io_partly_cloudy_night;
            case "hail": return R.string.wi_hail;
            case "thunderstorm": return R.string.wi_thunderstorm;
            case "tornado": return R.string.wi_tornado;
        }
        return 0;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public float getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(float uvIndex) {
        this.uvIndex = uvIndex;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getCloundCover() {
        return cloundCover;
    }

    public void setCloundCover(float cloundCover) {
        this.cloundCover = cloundCover;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
