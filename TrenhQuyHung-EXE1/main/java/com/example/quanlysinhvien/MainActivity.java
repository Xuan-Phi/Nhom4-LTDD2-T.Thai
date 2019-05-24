package com.example.quanlysinhvien;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.transition.Slide;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Kieu du lieu
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecycleView_Adapter recycleview_adapter;
    ArrayList<Khoa> data_khoa = new ArrayList<>();

    EditText txtHoTen, txtNamSinh, txtQueQuan;
    RadioButton rbtnNam, rbtnNu;
    Button btnThem, btnThoat;
    Spinner spnKhoa;
    int recycleview_pos = 0;

    SinhVienAdapter listview_adapter;
    private ListView lsvSinhVien_Nam;
    private ListView lsvSinhVien_Nu;
    ArrayList<SinhVien> sv_CNTT_Nam = new ArrayList<>();
    ArrayList<SinhVien> sv_CNTT_Nu = new ArrayList<>();
    ArrayList<SinhVien> sv_CoKhi_Nam = new ArrayList<>();
    ArrayList<SinhVien> sv_CoKhi_Nu = new ArrayList<>();
    ArrayList<SinhVien> sv_DuLich_Nam = new ArrayList<>();
    ArrayList<SinhVien> sv_DuLich_Nu = new ArrayList<>();
    ArrayList<SinhVien> sv_TiengAnh_Nam = new ArrayList<>();
    ArrayList<SinhVien> sv_TiengAnh_Nu = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setDataKhoa();
        setRecyclerViewAdapter();
        setListViewEvent(lsvSinhVien_Nam, sv_CNTT_Nam);
        setListViewEvent(lsvSinhVien_Nu, sv_CNTT_Nu);
        setEvent();







    }

    public void setEvent()
    {
        recycleview_adapter.setOnItemClickListener(new RecycleView_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                recycleview_pos = position;
                switch (position)
                {
                    case 0:
                        setListViewEvent(lsvSinhVien_Nam, sv_CNTT_Nam);
                        setListViewEvent(lsvSinhVien_Nu, sv_CNTT_Nu);
                        break;
                    case 1:
                        setListViewEvent(lsvSinhVien_Nam, sv_CoKhi_Nam);
                        setListViewEvent(lsvSinhVien_Nu, sv_CoKhi_Nu);
                        break;
                    case 2:
                        setListViewEvent(lsvSinhVien_Nam, sv_DuLich_Nam);
                        setListViewEvent(lsvSinhVien_Nu, sv_DuLich_Nu);
                        break;
                    case 3:
                        setListViewEvent(lsvSinhVien_Nam, sv_TiengAnh_Nam);
                        setListViewEvent(lsvSinhVien_Nu, sv_TiengAnh_Nu);
                        break;
                }
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSinhVien();
                listview_adapter.notifyDataSetChanged();
            }
        });


        lsvSinhVien_Nam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien sv = null;
                switch (recycleview_pos)
                {
                    case 0:
                        sv = sv_CNTT_Nam.get(position);
                        break;
                    case 1:
                        sv = sv_CoKhi_Nam.get(position);
                        break;
                    case 2:
                        sv = sv_DuLich_Nam.get(position);
                        break;
                    case 3:
                        sv = sv_TiengAnh_Nam.get(position);
                        break;
                }
                Toast.makeText(getApplicationContext(), sv.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        lsvSinhVien_Nu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien sv = null;
                switch (recycleview_pos)
                {
                    case 0:
                        sv = sv_CNTT_Nu.get(position);
                        break;
                    case 1:
                        sv = sv_CoKhi_Nu.get(position);
                        break;
                    case 2:
                        sv = sv_DuLich_Nu.get(position);
                        break;
                    case 3:
                        sv = sv_TiengAnh_Nu.get(position);
                        break;
                }
                Toast.makeText(getApplicationContext(), sv.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lsvSinhVien_Nam.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                switch (recycleview_pos)
                {
                    case 0:
                        sv_CNTT_Nam.remove(position);
                        setSoLuong(0);
                        break;
                    case 1:
                        sv_CoKhi_Nam.remove(position);
                        setSoLuong(1);
                        break;
                    case 2:
                        sv_DuLich_Nam.remove(position);
                        setSoLuong(2);
                        break;
                    case 3:
                        sv_TiengAnh_Nam.remove(position);
                        setSoLuong(3);
                        break;
                }
                listview_adapter.notifyDataSetChanged();

                return false;
            }
        });

        lsvSinhVien_Nu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                switch (recycleview_pos)
                {
                    case 0:
                        sv_CNTT_Nu.remove(position);
                        setSoLuong(0);
                        break;
                    case 1:
                        sv_CoKhi_Nu.remove(position);
                        setSoLuong(1);
                        break;
                    case 2:
                        sv_DuLich_Nu.remove(position);
                        setSoLuong(2);
                        break;
                    case 3:
                        sv_TiengAnh_Nu.remove(position);
                        setSoLuong(3);
                        break;
                }
                listview_adapter.notifyDataSetChanged();

                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.change,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.change:
                if (item.getTitle().toString().equals("Sunshine"))
                {

                    setTheme(R.style.MyAppTheme);
                    setContentView(R.layout.recycleview_item);
                    setContentView(R.layout.activity_main);
                    //this.recreate();
                    item.setIcon(R.drawable.light);
                    item.setTitle("Night");
                }
                else
                {

                    setTheme(R.style.AppTheme);
                    setContentView(R.layout.activity_main);
                   // this.recreate();
                    item.setIcon(R.drawable.hightlight);
                    item.setTitle("Sunshine");
                }
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void setControl()
    {
        txtHoTen = findViewById(R.id.txt_hoten);
        txtNamSinh = findViewById(R.id.txt_namsinh);
        txtQueQuan = findViewById(R.id.txt_quequan);
        rbtnNam = findViewById(R.id.rbtn_nam);
        rbtnNu = findViewById(R.id.rbtn_nu);
        spnKhoa = findViewById(R.id.spn_khoa);
        lsvSinhVien_Nam = findViewById(R.id.lsv_khoa_nam);
        lsvSinhVien_Nu = findViewById(R.id.lsv_khoa_nu);
        btnThem = findViewById(R.id.btn_them);
        btnThoat = findViewById(R.id.btn_thoat);
    }

    public void setListViewEvent(ListView lst,ArrayList<SinhVien> data)
    {
        listview_adapter = new SinhVienAdapter(this, R.layout.listview_item, data);
        lst.setAdapter(listview_adapter);
    }
    public void setRecyclerViewAdapter()
    {
        recyclerView = findViewById(R.id.recycleview_khoa);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recycleview_adapter = new RecycleView_Adapter(this, data_khoa, R.layout.recycleview_item);
        recyclerView.setAdapter(recycleview_adapter);
    }
    public void setDataKhoa()
    {
        data_khoa.add(new Khoa(R.drawable.cntt, "Công nghệ thông tin", sv_CNTT_Nam.size() + sv_CNTT_Nu.size()));
        data_khoa.add(new Khoa(R.drawable.cokhi, "Cơ khí", sv_CoKhi_Nam.size() + sv_CoKhi_Nu.size()));
        data_khoa.add(new Khoa(R.drawable.dulich, "Du lịch", sv_DuLich_Nam.size() + sv_DuLich_Nu.size()));
        data_khoa.add(new Khoa(R.drawable.tienganh, "Tiếng anh", sv_TiengAnh_Nam.size() + sv_TiengAnh_Nu.size()));
    }
    public void setSoLuong(int khoa)
    {
        int len = 0;
        switch (khoa)
        {
            case 0:
                len = sv_CNTT_Nam.size() + sv_CNTT_Nu.size();
                data_khoa.get(0).setSoluong(len);
                break;
            case 1:
                len = sv_CoKhi_Nam.size() + sv_CoKhi_Nu.size();
                data_khoa.get(1).setSoluong(len);
                break;
            case 2:
                len = sv_DuLich_Nam.size() + sv_DuLich_Nu.size();
                data_khoa.get(2).setSoluong(len);
                break;
            case 3:
                len = sv_TiengAnh_Nam.size() + sv_TiengAnh_Nu.size();
                data_khoa.get(3).setSoluong(len);
                break;
        }
        recycleview_adapter.notifyDataSetChanged();
    }

    public void addSinhVien()
    {
        if (checkData())
        {
            String hoten, quequan;
            int namsinh;
            boolean gioitinh;
            hoten = txtHoTen.getText().toString();
            namsinh = Integer.parseInt(txtNamSinh.getText().toString());
            if (rbtnNam.isChecked())
                gioitinh = true;
            else
                gioitinh = false;
            quequan = txtQueQuan.getText().toString();
            SinhVien sv = new SinhVien(hoten, gioitinh, namsinh, quequan);
                switch (spnKhoa.getSelectedItemPosition())
                {
                    case 0:
                        if (sv.isGioitinh())
                            sv_CNTT_Nam.add(sv);
                        else
                            sv_CNTT_Nu.add(sv);
                        setSoLuong(0);
                        break;
                    case 1:
                        if (sv.isGioitinh())
                            sv_CoKhi_Nam.add(sv);
                        else
                            sv_CoKhi_Nu.add(sv);
                        setSoLuong(1);
                        break;
                    case 2:
                        if (sv.isGioitinh())
                            sv_DuLich_Nam.add(sv);
                        else
                            sv_DuLich_Nu.add(sv);
                        setSoLuong(2);
                        break;
                    case 3:
                        if (sv.isGioitinh())
                            sv_TiengAnh_Nam.add(sv);
                        else
                            sv_TiengAnh_Nu.add(sv);
                        setSoLuong(3);
                        break;

                }

        }
    }
    public boolean checkData()
    {
        if (txtHoTen.getText().toString().length() == 0)
        {
            Toast.makeText(this, "Tên không được bỏ trống",Toast.LENGTH_SHORT).show();
            txtHoTen.requestFocus();
            return false;
        }
        if (!isNumber(txtNamSinh.getText().toString()))
        {
            Toast.makeText(this, "Lỗi năm sinh",Toast.LENGTH_SHORT).show();
            txtNamSinh.setText("");
            txtNamSinh.requestFocus();
            return false;
        }
        return true;
    }

    public boolean isNumber(String s)
    {
        if (s.length() == 0)
            return false;
        for (int i = 0; i < s.length(); i++)
        {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }
}
