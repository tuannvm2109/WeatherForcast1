package com.example.weatherforcast.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherforcast.R;
import com.example.weatherforcast.data.model.week.Forecastday;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {
    private List<Forecastday> forecastdays;
    private LayoutInflater inflater;
    private Context context;
    private DayCallBack callBack;
    private LinearLayout linearLayout;

    public DayAdapter(List<Forecastday> forecastdays, Context context) {
        this.forecastdays = forecastdays;
        this.context = context;
        inflater =LayoutInflater.from(context);
    }

    public void setCallBack(DayCallBack callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_day,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //linearLayout.setBackgroundColor(0xFF00FF00);
                callBack.onClick(viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.onBindData(forecastdays.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastdays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView tvNgay, tvNhietDo;

        public ViewHolder(View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_iteam);
            tvNgay = itemView.findViewById(R.id.tv_ngay_item);
            tvNhietDo = itemView.findViewById(R.id.tv_item_nhiet_do);
            linearLayout = itemView.findViewById(R.id.ln_item);
        }

        public void onBindData(Forecastday forecastday){
            Glide.with(context).load("http:"+forecastday.getDay().getCondition().getIcon()).into(imgItem);
            tvNgay.setText(fomatDate(forecastday.getDate()));
            tvNhietDo.setText(forecastday.getDay().getAvgtempC()+" °C");
        }
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
