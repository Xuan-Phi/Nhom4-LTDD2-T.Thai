package tdc.edu.vn.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    EditText ed1;
    Button bt1;
    String value, value1,value2;
    RecyclerView rc;
    RecyclerView.LayoutManager rclm;
    Adapter ad;
    ArrayList<SoThich> data = new ArrayList<>();
    String h;
    RadioButton r1,r2;
    CheckBox c1,c2,c3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setAdapter();
        setEvent();
    }

    private void setEvent() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTra()) return;

                value1 = ed1.getText().toString();
                SoThich pt = new SoThich();
                pt.setTxt1("Họ Tên: " + value1);
                value ="";
                if(c1.isChecked() == true){
                    value += "\n- "+c1.getText();
                }
                if (c2.isChecked() == true) {
                    value += "\n- " +c2.getText();
                    }
                if (c3.isChecked() == true) {
                    value += "\n- " +c3.getText();
                }

                pt.setTxt2("Sở Thích: " + value);
                Toast.makeText(getApplicationContext(), "Đã Lưu! ", Toast.LENGTH_LONG).show();
                if(r1.isChecked()){
                   pt.setImg(R.drawable.nam);
                 value2 = "Nam";
               }
               else if(r2.isChecked()){
                   pt.setImg(R.drawable.nu);
                    value2 = "Nữ";

               }
               pt.setTxt3("Giới Tính: " + value2);
                data.add(pt);
                ad.notifyDataSetChanged();
            }
        });
    }

    private void setControl() {

        tv1 = findViewById(R.id.tv1);
        ed1 = findViewById(R.id.ed1);
        bt1 = findViewById(R.id.bt1);
        r1 = findViewById(R.id.rbtnNam);
        r2 = findViewById(R.id.rbtnNu);
        c1 = findViewById(R.id.game);
        c2 = findViewById(R.id.sach);
        c3 = findViewById(R.id.bong);


    }

    public boolean kiemTra() {
        if (ed1.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Nhập Tên Đầy Đủ.!", Toast.LENGTH_LONG).show();
            ed1.requestFocus();
            return true;
        }
        return false;
    }

    void setAdapter(){
        rc = findViewById(R.id.rv1);
        rclm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rc.setLayoutManager(rclm);
        ad = new Adapter(this,data,R.layout.listviewitem);
        rc.setAdapter(ad);

    }


}
