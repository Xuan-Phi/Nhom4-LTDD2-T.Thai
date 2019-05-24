package com.example.exe4_trenhquyhung;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyVideoAdapter extends ArrayAdapter<MyVideo> {
    //Kiểu dữ liệu
    Context context;
    int layoutResourceId;
    ArrayList<MyVideo> data = null;

    //Constructor
    public MyVideoAdapter(Context context, int layoutResourceId, ArrayList<MyVideo> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    //Method
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        MyVideoHolder holder = null;
        if (row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            //Set data
            holder = new MyVideoHolder();
            holder.anhdaidien = (ImageView)row.findViewById(R.id.img_item_anhdaidien);
            holder.tvTieuDe = (TextView)row.findViewById(R.id.tv_item_tieude);
            holder.tvThoiGian = (TextView)row.findViewById(R.id.tv_item_thoigian);

            row.setTag(holder);

        }
        else
        {
            holder = (MyVideoHolder) row.getTag();
        }
        //Dinh nghia du lieu
        MyVideo video = data.get(position);

        holder.anhdaidien.setImageResource(video.getImg());
        holder.tvTieuDe.setText(video.getName());
        holder.tvThoiGian.setText(video.getTime());
        return  row;

    }
    static  class MyVideoHolder
    {
        ImageView anhdaidien;
        TextView tvTieuDe;
        TextView tvThoiGian;
    }
}