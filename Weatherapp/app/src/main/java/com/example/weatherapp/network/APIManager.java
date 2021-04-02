package com.example.weatherapp.network;

import com.learndemo.ss7_weather.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiWeatherManager {
    String SERVER = "http://dataservice.accuweather.com/";

    @GET("data.json")
    Call<Item> getData();

    @GET("datas.json")
    Call<List<Item>> getDatas();
    @GET("forecasts/v1/hourly/12hour/353412?apikey=FPi6yQiqXT6ckGgNAaOvMpNXjXOdezfN&language=vi-vn&metric=true")
    Call<List<Weather>>getWeather();
}
