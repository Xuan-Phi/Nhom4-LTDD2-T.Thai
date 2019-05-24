package com.example.admin.myapplication;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText txtTen, txtTacGia;
    RadioButton rbtnVanHoc, rbtnTruyen, rbtnKhac;
    Button btnThem;

    RecyclerView recyclerView,recycle_truyen,recycle_khac;
    RecyclerView.LayoutManager layoutManager;
    SachAdapter adapter;
    ArrayList<Sach> data = new ArrayList<>();
    ArrayList<Sach> data_truyen = new ArrayList<>();
    ArrayList<Sach> data_khac = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();

        data.add(new Sach("A", "Văn học", "Trịnh công sơn"));
        data_truyen.add(new Sach("doraemon", "truyen tranh", "xxxxx"));
        setAdapter(recyclerView,data);
        setAdapter(recycle_truyen,data_truyen);
        setAdapter(recycle_khac,data_khac);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them();
            }
        });
        //adapter.notifyDataSetChanged();

    }

    public void setControl()
    {
        txtTen = findViewById(R.id.tensach);
        txtTacGia = findViewById(R.id.tentg);
        rbtnVanHoc = findViewById(R.id.rbtn_vanhoc);
        rbtnTruyen = findViewById(R.id.rbtn_truyen);
        rbtnKhac = findViewById(R.id.rbtn_khac);
        btnThem = findViewById(R.id.btnThem);
        recyclerView = findViewById(R.id.recycleview);
        recycle_truyen = findViewById(R.id.recycleview_2);
        recycle_khac = findViewById(R.id.recycleview_3);
    }
    public void them() {
        String ten = txtTen.getText().toString();
        String tacgia = txtTacGia.getText().toString();
        String loaisach = "";
        Sach s = null;
        if (rbtnVanHoc.isChecked()) {
            loaisach = "Văn học";
             s = new Sach(ten, loaisach, tacgia);
            data.add(s);
           setAdapter(recyclerView,data);
        } else if (rbtnTruyen.isChecked()) {
            loaisach = "Truyện";
            s = new Sach(ten, loaisach, tacgia);
            data_truyen.add(s);
            setAdapter(recycle_truyen,data_truyen);
        } else {
            loaisach = "Khác";
             s = new Sach(ten, loaisach, tacgia);
            data_khac.add(s);
            setAdapter(recycle_khac,data_khac);

        }
    }



    public void setAdapter(RecyclerView recyclerView,ArrayList<Sach> sach)
    {

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SachAdapter(this, sach, R.layout.list_item_layout);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.light:
                thelight();
                return true;
            case R.id.hightlight:
                light();
                default:
                    return super.onOptionsItemSelected(item);


        }

    }

    private void thelight(){
        setContentView(R.layout.stylesmoi);
    }
    private void light(){
        setContentView(R.layout.activity_main);
    }
}
