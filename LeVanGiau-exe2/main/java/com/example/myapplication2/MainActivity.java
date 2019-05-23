package com.example.myapplication2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.AttributedCharacterIterator;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    //Sensor
    SensorManager sensorManager;
    private Sensor accelerometer;
   // private Sensor gyroscope;
   // private float vibrateThreshold = 0;
   // private float fX, fY, fZ;

    Button btnLen, btnXuong, btnTrai, btnPhai;
    TextView tvThongTin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setSensor();
    }

    public void setControl()
    {
        drawingView = findViewById(R.id.view);
    }

    DrawingView drawingView;


    public void setSensor()
    {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
        {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() ==  Sensor.TYPE_ACCELEROMETER)
        {
            //viet ham
            doiViTri(event);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void doiViTri(SensorEvent  event)
    {
        float deltaX =  event.values[0];
        float deltaY =   event.values[1];
        float deltaZ =   event.values[2];
        if (deltaX > 0)
        {
            drawingView.move(4);
        }
        else if (deltaX < 0)
        {
            drawingView.move(3);
        }
        else
        {

        }
        float y = 10;
        if (deltaY < y && deltaZ > 0)
        {
            drawingView.move(1);
        }
        else if (deltaY < y && deltaZ < 0)
        {
            drawingView.move(2);
        }
    }
}
