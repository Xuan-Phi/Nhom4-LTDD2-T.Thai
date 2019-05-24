package com.example.trenhquyhung_exe2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLen, btnXuong, btnTrai, btnPhai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    public void setControl()
    {
        btnLen = findViewById(R.id.btn_len);
        btnXuong = findViewById(R.id.btn_xuong);
        btnTrai = findViewById(R.id.btn_trai);
        btnPhai = findViewById(R.id.btn_phai);
        drawingView =  findViewById(R.id.view);
    }

    DrawingView drawingView;
    public void setEvent()
    {

        btnLen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(1);
            }
        });

        btnXuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(2);
            }
        });

        btnTrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(3);
            }
        });

        btnPhai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(4);
            }
        });
    }
}
