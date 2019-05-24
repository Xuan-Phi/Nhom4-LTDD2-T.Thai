package com.example.exe4_trenhquyhung;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Kieu du lieu
    ListView listView;
    MyVideoAdapter adapter;
    ArrayList<MyVideo> data = new ArrayList<>();

    VideoView videoView;
    MediaController mediaController;
    ImageView imgAnhDaiDien, imgItemAnhDaiDien;
    TextView tvTieuDe, tvItemTieuDe, tvThoiGian, tvItemThoiGian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        initData();
        setAdapter();
        setVideo(data.get(0).getId());
        setEvent();

    }

    public void setControl()
    {
        listView = findViewById(R.id.listView);
        videoView = findViewById(R.id.videoView);
        imgAnhDaiDien = findViewById(R.id.img_anhdaidien);
        imgItemAnhDaiDien = findViewById(R.id.img_item_anhdaidien);
        tvTieuDe = findViewById(R.id.tv_tieude);
        tvThoiGian = findViewById(R.id.tv_thoigian);
        tvItemTieuDe = findViewById(R.id.tv_item_tieude);
        tvItemThoiGian = findViewById(R.id.tv_item_thoigian);
    }

    public void initData()
    {
        data.add(new MyVideo(R.raw.soundtest, R.drawable.hhdt8, "Hoạt hình đám cưới vui nhộn", setTimeTotal(R.raw.soundtest)));
        data.add(new MyVideo(R.raw.soundtest, R.drawable.hhdt4, "Lời tỏ tình dễ thương", "00:00"));
        data.add(new MyVideo(R.raw.soundtest, R.drawable.jerrycousin, "Tom và Jerry - Jerry's Cousin", "00:00"));
        data.add(new MyVideo(R.raw.soundtest, R.drawable.litteorphan, "Tom và Jerry - The Litte Orphan", "00:00"));
    }

    public void setAdapter()
    {
        adapter = new MyVideoAdapter(this, R.layout.myvideo_item, data);
        listView.setAdapter(adapter);
    }

    public String setTimeTotal(int id)
    {
        String s = "";
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));
        SimpleDateFormat dinhdang = new SimpleDateFormat("mm:ss");
        s = dinhdang.format(videoView.getDuration()) + "";
        return s;
    }
    public void setVideo(int id)
    {
        // Tạo bộ điều khiển
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));
            mediaController = new MediaController(MainActivity.this);
            // Sét đặt bộ điều khiển cho VideoView.
            videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();
    }

    public void setEvent()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + data.get(position).getId()));
                setVideo(data.get(position).getId());
            }
        });
    }
    public void setCurrentVideoPlaying(MyVideo video)
    {

    }

}
