package com.example.weatherapp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.Weather;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Weather> listWeather;
   public HomeAdapter(Activity activity,List<Weather> listWeather){
        this.activity = activity;
        this.listWeather = listWeather;
    }
    public void reloadData(List<Weather> list) {
        this.listWeather = list;
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_home, parent, false);
        HomeHolder homeHolder = new HomeHolder(itemView);
        return homeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeHolder homeHolder = (HomeHolder) holder;
        homeHolder.tvDateTime.setText(model.getDate());
        homeHolder.tvContent.setText(model.getContent().getDescription());
        homeHolder.tvTitle.setText(model.getTitle());
        Glide.with(activity).load(model.getImage()).into(homeHolder.ivCover);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTitle, tvContent;
        ImageView ivCover;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            ivCover = itemView.findViewById(R.id.ivCover);
        }
    }
}
