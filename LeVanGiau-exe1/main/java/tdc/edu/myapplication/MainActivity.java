package tdc.edu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<ThucDon> data = new ArrayList<>();
    EditText txtAn, txtUong;
    Button btnNhap;
    RadioButton rbsang, rbtrua, rbtoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAdapter();
        setEvent();
    }

    void setAdapter() {
        txtAn = (EditText) findViewById(R.id.txtAn);
        txtUong = (EditText) findViewById(R.id.txtUong);
        rbsang = (RadioButton) findViewById(R.id.rbnsang);
        rbtrua = (RadioButton) findViewById(R.id.rbntrua);
        rbtoi = (RadioButton) findViewById(R.id.rbntoi);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        recyclerView = findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, data, R.layout.listview_item_row);
        recyclerView.setAdapter(adapter);
    }

    void setEvent() {
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtAn.getText().toString().equals("") || txtUong.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (rbsang.isChecked()) {
                        data.add(new ThucDon(R.drawable.cua, txtAn.getText().toString(), txtUong.getText().toString(), "Bữa Sáng"));
                    } else if (rbtrua.isChecked()) {
                        data.add(new ThucDon(R.drawable.coca, txtAn.getText().toString(), txtUong.getText().toString(), "Bữa trưa"));
                    } else {
                        data.add(new ThucDon(R.drawable.com, txtAn.getText().toString(), txtUong.getText().toString(), "Bữa tối"));


                    }
                    adapter.notifyDataSetChanged();
                    txtUong.setText("");
                    txtAn.setText("");
                    txtAn.requestFocus();
                }
            }
        });
    }
}

