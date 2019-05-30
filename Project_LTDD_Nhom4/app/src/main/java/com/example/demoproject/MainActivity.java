package com.example.demoproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //1. Kieu du lieu

    //Music
    MediaPlayer mediaPlayer;
    ImageButton imvPlay, imvNext, imvPrev;
    TextView tvTenBaiHat, tvTimeDau, tvTimeCuoi;
    SeekBar seekBar;
    ArrayList<Song> arrayListSong;
    int position = 0;
    View view;

    Intent intent;

    //PieChart
    PieChart pieChart;
    ArrayList<PieEntry> yEntrys;
    PieDataSet pieDataSet;
    PieData pieData;

    //Control
    TextView tvDate, tvDistance, tvCalo;

    //Floating
    FloatingActionButton fab, fabProfile, fabAcchivement, fabMusic;
    boolean anhien = false;


    //Intent
    String name;
    int age, height, weight;
    String gender;
    int stepSize = 0; //Khoang cach buoc chan
    int totalstep = 0; //Tong so buoc

    //Sensor
    private SensorManager mSensorManager;
    private Sensor mStepSensor;
    public SensorEventListener mSensorEventListener = new SensorEventListener() {
        private float mStepOffset;
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (mStepOffset == 0) {
                mStepOffset = event.values[0];
            }
            int realstep = Math.round(event.values[0] - mStepOffset);
            //Thay đổi số trong pie
            setDataPieChartChanged((float)realstep);
            //Thay đổi số km
            tvDistance.setText("Km: " + Float.toString(Math.round(getDistanceRun(realstep, stepSize)*100)/100));
            //Thay đổi số calo
            tvCalo.setText("Cal: " + Float.toString(Math.round(getCalo(getDistanceRun(realstep, stepSize))*10)/10));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setCurrentTime();
        getInformation();
        setNav();
        setPieChart();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        setDataPieChartChanged(30);
        initSong();
        setEventSong();


    }
    public void setTimeTotal()
    {
        SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");
        tvTimeCuoi.setText(dinhdangGio.format(mediaPlayer.getDuration()) + "");
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void initSong()
    {
        arrayListSong = new ArrayList<>();
        arrayListSong.add(new Song("Running Song", R.raw.b1));
        arrayListSong.add(new Song("Beautiful day", R.raw.b1));
        arrayListSong.add(new Song("Nightmare for you", R.raw.b1));
        arrayListSong.add(new Song("Turn down for what", R.raw.b1));
    }
    private void updateTime()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");
                tvTimeDau.setText(dinhdangGio.format(mediaPlayer.getCurrentPosition()) + "");
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arrayListSong.size() - 1)
                            position = 0;
                        if (mediaPlayer.isPlaying())
                            mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(MainActivity.this, arrayListSong.get(position).getFile());
                        tvTenBaiHat.setText(arrayListSong.get(position).getTenbaihat());
                        mediaPlayer.start();
                        imvPlay.setImageResource(R.drawable.pause);
                        setTimeTotal();
                        updateTime();
                    }
                });
            }
        }, 100);
    }
    private void setEventSong()
    {
        setMediaPlayer();
        imvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    imvPlay.setImageResource(R.drawable.play);
                }
                else
                {
                    mediaPlayer.start();
                    imvPlay.setImageResource(R.drawable.pause);
                }
                setTimeTotal();
                updateTime();
            }
        });


        imvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arrayListSong.size() - 1){
                    position = 0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                //Khoi tao lai bai hat
                setMediaPlayer();
                mediaPlayer.start();
                imvPlay.setImageResource(R.drawable.pause);
                setTimeTotal();
                updateTime();
            }
        });

        imvPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < arrayListSong.size() - 1){
                    position = 0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                //Khoi tao lai bai hat
                setMediaPlayer();
                mediaPlayer.start();
                imvPlay.setImageResource(R.drawable.pause);
                setTimeTotal();
                updateTime();

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }
    private void setMediaPlayer()
    {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arrayListSong.get(position).getFile());
        tvTenBaiHat.setText(arrayListSong.get(position).getTenbaihat());
    }
    private void setControl()
    {
        tvDate = findViewById(R.id.tv_date);
        tvDistance = findViewById(R.id.tv_distance);
        tvCalo = findViewById(R.id.tv_calo);

        //Music
        imvPrev = findViewById(R.id.imv_prev);
        imvPlay = findViewById(R.id.imv_play);
        imvNext = findViewById(R.id.imv_next);
        tvTenBaiHat = findViewById(R.id.tv_song_name);
        tvTimeDau = findViewById(R.id.tv_song_timedau);
        tvTimeCuoi =  findViewById(R.id.tv_song_timecuoi);
        seekBar = findViewById(R.id.seekbar);
        view = findViewById(R.id.music_view);
    }
    public void setCurrentTime()
    {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(currentTime);
        tvDate.setText(formattedDate);
    }
    private void getInformation()
    {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = Integer.parseInt(intent.getStringExtra("age"));
        gender = intent.getStringExtra("gender");
        height = Integer.parseInt(intent.getStringExtra("height"));
        weight = Integer.parseInt(intent.getStringExtra("weight"));
        stepSize = Integer.parseInt(intent.getStringExtra("stepsize"));
        totalstep = Integer.parseInt(intent.getStringExtra("step"));
    }
    public float getCalo(float distance)
    {
        return (float)(100*distance/1.6);
        //100 cal = 2000 bước = 1,6km
    }
    public float getDistanceRun(int steps, int stepSize){
        float distance = (float)(steps*stepSize)/(float)100000;
        return distance;
    }
    public void setDataPieChartChanged(float step)
    {
        float muctieu = totalstep - step;
        pieChart.setCenterText(String.valueOf((int)step));
        yEntrys.set(0, new PieEntry(step, 0));
        yEntrys.set(1, new PieEntry(muctieu, 1));
        pieDataSet.notifyDataSetChanged();
       pieData.notifyDataChanged();
        pieChart.invalidate();

    }
    private void setPieChart()
    {
        int step = 1000;
        pieChart = findViewById(R.id.piechart);
        pieChart.setHoleRadius(80f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText(String.valueOf(step));
        pieChart.setCenterTextSize(30);

        yEntrys = new ArrayList<>();
        yEntrys.add(new PieEntry(25f, 0));
        yEntrys.add(new PieEntry(75f, 1));

         pieDataSet = new PieDataSet(yEntrys, "");
        pieChart.setEnabled(false);
        pieDataSet.setDrawValues(false);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        pieDataSet.setColors(colors);

        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    private void setNav()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabProfile = (FloatingActionButton) findViewById(R.id.fab_profile);
        fabMusic = (FloatingActionButton) findViewById(R.id.fab_music);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(anhien == false)
                {
                    hienThi();
                    anhien = true;
                }
                else
                {
                    an();
                    anhien=false;
                }
            }
        });

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
       //         this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       // drawer.addDrawerListener(toggle);
       // toggle.syncState();

       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
      //  navigationView.setNavigationItemSelectedListener(this);
        //getInformation();

        fabProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Information.class);
                intent.putExtra("name", name);
                intent.putExtra("age", String.valueOf(age));
                intent.putExtra("gender",gender);
                intent.putExtra("height", String.valueOf(height));
                intent.putExtra("weight", String.valueOf(weight));
                intent.putExtra("stepsize", String.valueOf(stepSize));
                intent.putExtra("step", String.valueOf(totalstep));
                startActivity(intent);
                finish();
            }
        });
        fabMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.VISIBLE);
            }
        });

    }
    private void hienThi(){
        fabProfile.show();
        fabMusic.show();
    }
    private void an(){
        fabProfile.hide();
       fabMusic.hide();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onResume() {
        super.onResume();
        mStepSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (mStepSensor != null)
        {
            mSensorManager.registerListener(mSensorEventListener, mStepSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Sensor not found", Toast.LENGTH_SHORT).show();
            showAlertDialog();
        }
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device not support");
        builder.setMessage("Sensor not found! App will be shutdown");
        builder.setCancelable(false);

        builder.setNegativeButton("I Got", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
    }


}




//Có nhiều cách để xác định độ dài bước: bạn có thể tự đo,
//ước tính bằng cách nhân chiều cao của mình theo centimet với 0,415 cho nam và 0,413 cho nữ

