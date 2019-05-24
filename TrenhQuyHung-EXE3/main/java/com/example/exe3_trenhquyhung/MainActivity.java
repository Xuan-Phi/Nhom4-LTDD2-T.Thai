package com.example.exe3_trenhquyhung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Kieu du lieu
    TextView tvTitle;
    ImageView imgGhost1, imgGhost2, imgGhost3;
    Button btnPlay;
    boolean isCheckGhost1 = false, isCheckGhost2 = false;

    Animation animGhostBlink, animGhostMove;
    Animation animDie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animDie = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ghostdie);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();



    }


    public void setControl()
    {
        tvTitle = findViewById(R.id.tv_title);
        imgGhost1 = findViewById(R.id.img_ghost1);
        imgGhost2 = findViewById(R.id.img_ghost2);
      //  imgGhost3 = findViewById(R.id.img_ghost3);
        btnPlay = findViewById(R.id.btn_play);
    }

    public void setEvent()
    {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvTitle.setText("Touch to catch the ghost!");
                isCheckGhost1 = isCheckGhost2 = false;

                imgGhost1.setImageResource(R.drawable.ghost);
                animGhostBlink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ghostblink);
                imgGhost1.startAnimation(animGhostBlink);

                imgGhost2.setImageResource(R.drawable.ghost);
                animGhostMove = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ghostmove);
                imgGhost2.startAnimation(animGhostMove);
                //imgGhost2.clearAnimation();
            }
        });
        imgGhost1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    imgGhost1.clearAnimation();
                    imgGhost1.setImageResource(R.drawable.ghostdie);
                   // animDie = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ghostdie);
                    imgGhost1.startAnimation(animDie);
                    isCheckGhost1 = true;
                if (isCheckGhost2 == true)
                    tvTitle.setText("You Win!");
                return false;
            }
        });

        imgGhost2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                imgGhost2.clearAnimation();
                imgGhost2.setImageResource(R.drawable.ghostdie);
                // animDie = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ghostdie);
                imgGhost2.startAnimation(animDie);
                isCheckGhost2 = true;
                if (isCheckGhost1 == true)
                    tvTitle.setText("You Win!");

                return false;
            }
        });



    }
}
