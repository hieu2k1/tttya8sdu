package com.example.weatherapp.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.adapter.HomeAdapter;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.network.APIManager;
import com.learndemo.ss7_weather.ApiWeatherManager;
import com.learndemo.ss7_weather.R;
import com.learndemo.ss7_weather.adapter.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListWeather;
    List<Weather> listData;
    HomeAdapter adapter;
    TextView tvValue,tvIconPhrase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIconPhrase = findViewById(R.id.tvIconPhrase);
        tvValue = findViewById(R.id.tvValue);

        getListData();

        listData = new ArrayList<>();
        adapter = new HomeAdapter(MainActivity.this,listData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        rvListWeather = findViewById(R.id.rvListWeather);
        rvListWeather.setLayoutManager(layoutManager);
        rvListWeather.setAdapter(adapter);
    }

    private void getListData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER)
                .addConverterFactory(ConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);
        service.getListData().enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if (response.body() != null){
                    listData = response.body();
                    adapter.reloadData(listData);
                    tvIconPhrase.setText(listData.get(0).getIconPhrase());
                    tvValue.setText(String.valueOf(listData.get(0).getTemperature().getValue())+ "º");
                }
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_LONG).show();
            }
        });
    }

}
