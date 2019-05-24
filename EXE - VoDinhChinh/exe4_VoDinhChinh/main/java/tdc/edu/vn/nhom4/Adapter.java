package tdc.edu.vn.nhom4;

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

public class Adapter extends RecyclerView.Adapter<Adapter.Myviewholder>{
    //Kieu du lieu
    Context context;
    ArrayList<PhepTinh> data = null;
    int layoutResourceId;

    public Adapter(Context context, ArrayList<PhepTinh> data, int layoutResourceId) {
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(layoutResourceId, viewGroup, false);
        return new Myviewholder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        myviewholder.tvSoA.setText(data.get(i).getSoA());
        myviewholder.tvPT.setText(data.get(i).getPt());
        myviewholder.tvSoB.setText(data.get(i).getSoB());
        myviewholder.tvKQ.setText(data.get(i).getKq());
        myviewholder.img.setImageResource(data.get(i).getImg());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvSoA, tvSoB, tvPT, tvKQ;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvSoA = itemView.findViewById(R.id.tvSoA);
            tvSoB = itemView.findViewById(R.id.tvSoB);
            tvPT = itemView.findViewById(R.id.tvphepTinh);
            tvKQ = itemView.findViewById(R.id.tvKQ);
        }
    }
}
