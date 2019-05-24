package com.example.quanlysinhvien;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleView_Adapter extends RecyclerView.Adapter<RecycleView_Adapter.MyViewHolder> {
    //Kieu du lieu
    Context context;
    ArrayList<Khoa> data;
    int layoutResourceId;

    public RecycleView_Adapter(Context context, ArrayList<Khoa> data, int layoutResourceId) {
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(layoutResourceId, viewGroup, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.imgKhoa.setImageResource(data.get(i).getImg());
        myViewHolder.tvTenKhoa.setText(data.get(i).getTenkhoa().toString());
        myViewHolder.tvSoLuong.setText(String.valueOf(data.get(i).getSoluong()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgKhoa;
        TextView tvTenKhoa;
        TextView tvSoLuong;
        public MyViewHolder(final View itemView)
        {
            super(itemView);
            imgKhoa = itemView.findViewById(R.id.img_khoa);
            tvTenKhoa = itemView.findViewById(R.id.tv_tenkhoa);
            tvSoLuong = itemView.findViewById(R.id.tv_soluong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }
    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
