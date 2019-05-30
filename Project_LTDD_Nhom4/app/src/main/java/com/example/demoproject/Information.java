package com.example.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Information extends AppCompatActivity {

    EditText txtName, txtAge, txtHeight, txtWeight, txtStepSize, txtStep;
    ImageView img;
    Button btnBack, btnSave;
    String name, age, height, weigth, stepsize, step;
    boolean isSave = false;
    String gender;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setControl();
        getData();
        setEvent();

    }


    public void getData()
    {
        Intent intent = getIntent();
        txtName.setText(intent.getStringExtra("name"));
        txtAge.setText(intent.getStringExtra("age"));
        txtHeight.setText(intent.getStringExtra("height"));
        txtWeight.setText(intent.getStringExtra("weight"));
        txtStepSize.setText(intent.getStringExtra("stepsize"));
        txtStep.setText(intent.getStringExtra("step"));
        gender = intent.getStringExtra("gender");
        if (gender.equals("boy"))
            img.setImageResource(R.drawable.boy);
        else
            img.setImageResource(R.drawable.girl);
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

        if (!isNumber(txtStepSize.getText().toString()))
        {
            Toast.makeText(this, "Invalid StepSize", Toast.LENGTH_SHORT).show();
            return false;
        }

        int stepsize = Integer.parseInt(txtStepSize.getText().toString());
        if (stepsize <= 0 || stepsize > 100)
        {
            Toast.makeText(this, "Invalid StepSize", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isNumber(txtStep.getText().toString()))
        {
            Toast.makeText(this, "Invalid StepSize", Toast.LENGTH_SHORT).show();
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
        txtStep = findViewById(R.id.txt_infor_step);
        txtName = findViewById(R.id.txt_infor_name);
        txtAge = findViewById(R.id.txt_infor_age);
        txtHeight = findViewById(R.id.txt_infor_height);
        txtWeight = findViewById(R.id.txt_infor_weight);
        txtStepSize = findViewById(R.id.txt_infor_stepsize);
        img = findViewById(R.id.img_infor);
        btnBack = findViewById(R.id.btn_infor_back);
        btnSave = findViewById(R.id.btn_infor_save);

    }

    public void setEvent()
    {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData())
                {
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    name = txtName.getText().toString();
                    age = txtAge.getText().toString();
                    weigth = txtWeight.getText().toString();
                    height = txtHeight.getText().toString();
                    stepsize = txtStepSize.getText().toString();
                    step = txtStep.getText().toString();
                    isSave = true;
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSave)
                {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("height", height);
                    intent.putExtra("weight", weigth);
                    intent.putExtra("stepsize", stepsize);
                    intent.putExtra("gender", gender);
                    intent.putExtra("step", step);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }
}
