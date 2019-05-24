package tdc.edu.vn.nhom4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //1. Kieu du lieu
    EditText txtSoA, txtSoB, txtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<PhepTinh> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setAdapter();
        setEvent();


    }

    public void setControl()
    {
        txtSoA = (EditText)findViewById(R.id.soA);
        txtSoB = (EditText)findViewById(R.id.soB);
        txtKQ = (EditText)findViewById(R.id.ketQua);
        btnCong = (Button)findViewById(R.id.btnCong);
        btnTru = (Button)findViewById(R.id.btnTru);
        btnNhan = (Button)findViewById(R.id.btnNhan);
        btnChia = (Button)findViewById(R.id.btnChia);
    }

    public boolean isPheptinh(String pheptinh)
    {
        int soA = Integer.parseInt(txtSoA.getText().toString());
        int soB = Integer.parseInt(txtSoB.getText().toString());
        int kq = Integer.parseInt(txtKQ.getText().toString());

        switch (pheptinh)
        {
            case "+":
                if (soA + soB == kq)
                    return true;
                else
                    return false;
            case "-":
                if (soA - soB == kq)
                    return true;
                else
                    return false;
            case "*":
                if (soA * soB == kq)
                    return true;
                else
                    return false;
            case "/":
                if (soB == 0)
                    return false;
                if (soA / soB == kq)
                    return true;
                else
                    return false;
        }
        return false;
    }

    public void setEvent()
    {


        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtSoA.getText().toString());
                phepTinh.setSoB(txtSoB.getText().toString());
                phepTinh.setKq(txtKQ.getText().toString());
                phepTinh.setPt("+");
                if (isPheptinh("+"))
                    phepTinh.setImg(R.drawable.ic_check_black_24dp);
                    //Toast.makeText(getApplicationContext(), "Dung", Toast.LENGTH_LONG).show();

                else
                    phepTinh.setImg(R.drawable.ic_clear_black_24dp);
                    //Toast.makeText(getApplicationContext(), "Sai", Toast.LENGTH_LONG).show();

                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtSoA.getText().toString());
                phepTinh.setSoB(txtSoB.getText().toString());
                phepTinh.setKq(txtKQ.getText().toString());
                phepTinh.setPt("-");
                if (isPheptinh("-"))
                    phepTinh.setImg(R.drawable.ic_check_black_24dp);
                    //Toast.makeText(getApplicationContext(), "Dung", Toast.LENGTH_LONG).show();
                else
                    phepTinh.setImg(R.drawable.ic_clear_black_24dp);
                    //Toast.makeText(getApplicationContext(), "Sai", Toast.LENGTH_LONG).show();

                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtSoA.getText().toString());
                phepTinh.setSoB(txtSoB.getText().toString());
                phepTinh.setKq(txtKQ.getText().toString());
                phepTinh.setPt("*");
                if (isPheptinh("*"))
                    phepTinh.setImg(R.drawable.ic_check_black_24dp);
                   // Toast.makeText(getApplicationContext(), "Dung", Toast.LENGTH_LONG).show();
                else
                    phepTinh.setImg(R.drawable.ic_clear_black_24dp);
                   // Toast.makeText(getApplicationContext(), "Sai", Toast.LENGTH_LONG).show();

                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });

        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtSoA.getText().toString());
                phepTinh.setSoB(txtSoB.getText().toString());
                phepTinh.setKq(txtKQ.getText().toString());
                phepTinh.setPt("/");
                if (isPheptinh("/"))
                    phepTinh.setImg(R.drawable.ic_check_black_24dp);
                    //Toast.makeText(getApplicationContext(), "Dung", Toast.LENGTH_LONG).show();
                else
                    phepTinh.setImg(R.drawable.ic_clear_black_24dp);
                    //.makeText(getApplicationContext(), "Sai", Toast.LENGTH_LONG).show();

                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }

        });



    }

    public void setAdapter()
    {
        recyclerView = findViewById(R.id.recycleview);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, data, R.layout.list_item_layout);
        recyclerView.setAdapter(adapter);
    }
}
