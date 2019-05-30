package com.example.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class DangNhap extends AppCompatActivity {

    EditText txtName, txtAge, txtHeight, txtWeight;
    RadioButton rbtnMen, rbtnWomen;
    Button btnExit, btnSubmit;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        setControl();
        intent = new Intent(this, MainActivity.class);
        setEvent();
    }

    public boolean checkData()
    {
        if (txtName.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please input data at Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (txtName.getText().toString().length() > 10)
        {
            Toast.makeText(this, "Name is too long", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isNumber(txtAge.getText().toString()))
        {
            Toast.makeText(this, "Invalid Age", Toast.LENGTH_SHORT).show();
            return false;
        }
        int age = Integer.parseInt(txtAge.getText().toString());
        if (age <= 0 || age > 100)
        {
            Toast.makeText(this, "Invalid Age", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isNumber(txtHeight.getText().toString()))
        {
            Toast.makeText(this, "Invalid Height", Toast.LENGTH_SHORT).show();
            return false;
        }

        int height = Integer.parseInt(txtHeight.getText().toString());
        if (height <= 0 || height > 300)
        {
            Toast.makeText(this, "Invalid Height", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isNumber(txtWeight.getText().toString()))
        {
            Toast.makeText(this, "Invalid Weight", Toast.LENGTH_SHORT).show();
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
        return  true;
    }
    public void setControl()
    {
        txtName = findViewById(R.id.txt_name);
        txtAge = findViewById(R.id.txt_age);
        txtHeight = findViewById(R.id.txt_height);
        txtWeight = findViewById(R.id.txt_weight);
        rbtnMen = findViewById(R.id.rbtn_men);
        rbtnWomen = findViewById(R.id.rbtn_women);
        btnExit = findViewById(R.id.btn_exit);
        btnSubmit = findViewById(R.id.btn_submit);

    }

    public void setEvent()
    {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData())
                {
                    String gender = "boy";
                    intent.putExtra("name", txtName.getText().toString());
                    intent.putExtra("age", txtAge.getText().toString());
                    if (!rbtnMen.isChecked())
                        gender = "girl";
                    intent.putExtra("gender", gender);
                    intent.putExtra("height", txtHeight.getText().toString());
                    intent.putExtra("weight", txtWeight.getText().toString());
                    int height = Integer.parseInt(txtHeight.getText().toString());
                    int stepsize = (int)Math.round((0.415*height*10)/10);
                    intent.putExtra("stepsize", String.valueOf(stepsize));
                    intent.putExtra("step", "1000");
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
