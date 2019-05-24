package com.example.quanlysinhvien;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    Context context;
    int layoutResourceId;
    ArrayList<SinhVien> data;

    public SinhVienAdapter(Context context, int layoutResourceId, ArrayList<SinhVien> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View row = convertView;
        SinhVienHolder holder = null;
        if (row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,    parent, false);

            holder = new SinhVienHolder();
            holder.imgGioiTinh = (ImageView)row.findViewById(R.id.img_gioitinh);
            holder.tvHoTen = (TextView)row.findViewById(R.id.tv_hoten);
            holder.tvNamSinh = (TextView)row.findViewById(R.id.tv_namsinh);
            holder.tvQueQuan = (TextView)row.findViewById(R.id.tv_quequan);

            row.setTag(holder);
        }
        else
        {
            holder = (SinhVienHolder)row.getTag();
        }

        SinhVien sv = data.get(position);
        holder.tvHoTen.setText(sv.getHoten().toString());
        if (sv.isGioitinh())
        {
            holder.imgGioiTinh.setImageResource(R.drawable.boy);
        }
        else
        {
            holder.imgGioiTinh.setImageResource(R.drawable.girl);
        }
        holder.tvNamSinh.setText(String.valueOf(sv.getNamsinh()));
        holder.tvQueQuan.setText(sv.getQuequan());

        return row;
    }

    static class SinhVienHolder{
        TextView tvHoTen;
        ImageView imgGioiTinh;
        TextView tvNamSinh;
        TextView tvQueQuan;
    }
}
