package tdc.edu.vn.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    Context context;
    ArrayList<SoThich> data;
    int layoutRS;

    public Adapter(Context context, ArrayList<SoThich> data, int layoutRS) {
        this.context = context;
        this.data = data;
        this.layoutRS = layoutRS;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(layoutRS,viewGroup,false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,final int i) {
        myViewHolder.txt1.setText(data.get(i).getTxt1());
        myViewHolder.txt2.setText(data.get(i).getTxt2());
        myViewHolder.txt3.setText(data.get(i).getTxt3());
        myViewHolder.img.setImageResource(data.get(i).getImg());
        myViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveItem(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt1,txt2,txt3,txt4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            txt3 = itemView.findViewById(R.id.txt3);
            img = itemView.findViewById(R.id.img);

        }
    }
    public void RemoveItem(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
}
