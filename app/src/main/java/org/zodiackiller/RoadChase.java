package org.zodiackiller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.zodiackiller.db.DatabaseHelper;

import java.util.concurrent.TimeUnit;

public class RoadChase extends AppCompatActivity {

    private TextView _tv, high;

    ImageView plCar, im1, im2, im3, im4, im5, im6, im7, im8, im9, im10, im11, im12, im13;

    CountDownTimer cou2, cou3, cou4;
    DatabaseHelper myDb;
    MediaPlayer mpChase;
    static int Chasepoints, Chasefail=0, plCarleft,plCarRight,plCarTop;
    int TurnLeft=0, played=0, TurnRight=0, firstroundx, firstroundy, secondroundx, secondroundy, thirdroundx, thirdroundy;

    int car1left, car1right, car1Bottom, car2left, car2right, car4left, car3left, car3right, car4right;
    int finalplcarleft, finalplcarright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        KillerFacePuzzle.mpKiller.stop();
        scenetwo.mpr.stop();
        mpChase = MediaPlayer.create(this, R.raw.raceending);

        if(played==0) {
            mpChase.start();
            played++;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_chase);
        myDb = new DatabaseHelper(this);_tv  = (TextView) findViewById(R.id.TimeLeft);
        plCar = (ImageView) findViewById(R.id.playerCar); im1 = (ImageView) findViewById(R.id.Car1);
        im2 = (ImageView) findViewById(R.id.Car2); im3 = (ImageView) findViewById(R.id.Car3);
        im4 = (ImageView) findViewById(R.id.Car4); im5 = (ImageView) findViewById(R.id.Car5);
        im6 = (ImageView) findViewById(R.id.Car6); im7 = (ImageView) findViewById(R.id.Car7);
        im8 = (ImageView) findViewById(R.id.Car8); im9 = (ImageView) findViewById(R.id.Car9);
        im10 = (ImageView) findViewById(R.id.Car10); im11 = (ImageView) findViewById(R.id.Car11);
        im12 = (ImageView) findViewById(R.id.Car12); im13 = (ImageView) findViewById(R.id.Car13);

        CountDownTimer coun =  new CountDownTimer(40000, 1000) {

            public void onTick(long millisUntilFinished) {
                _tv.setText(String.format("%d sec(s)",

                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                HighScore.point +=25;

            }
            public void onFinish() {
                if (Chasefail == 0) {

                    cancel();

                    HighScore.score = "" + HighScore.point;

                    boolean isInserted = myDb.insertData(HighScore.name, HighScore.score);

                    finish();

                    Toast.makeText(RoadChase.this, "Hey, Level completed !!", Toast.LENGTH_SHORT).show();
                    Intent complete = new Intent(RoadChase.this, MainActivity.class);
                    startActivity(complete);
                }
            }
        }.start();

        cou2 = new CountDownTimer(9000, 500) {
            @Override
            public void onTick(long l) {
                im4.setVisibility(View.VISIBLE); im5.setVisibility(View.VISIBLE); im6.setVisibility(View.VISIBLE);

                car1left = im4.getLeft();  car1right = im4.getRight(); car1Bottom = (int)im4.getY();
                car2left = im5.getLeft();  car2right = im5.getRight();
                car3left = im6.getLeft();  car3right = im6.getRight();

                plCarleft = firstroundx; plCarRight = firstroundy; plCarTop = plCar.getTop();
                finalplcarleft=0; finalplcarright=0;

                if (im4.getY() < 750) im4.setY(im4.getY() + 45);    if (im5.getY() < 750) im5.setY(im5.getY() + 45);
                if (im6.getY() < 750) im6.setY(im6.getY() + 45);

                car1Bottom+=45;

                if(plCarTop<=car1Bottom) {

                    finalplcarleft = plCarleft-(TurnLeft*40)+(TurnRight*40);
                    finalplcarright = plCarRight+(TurnRight*40)-(TurnLeft*40);

                    if ((finalplcarleft < car1right) || ((finalplcarleft < car2left) && (finalplcarright>car2left))
                            || ((finalplcarright > car2left) && (finalplcarleft<car2right)) || (finalplcarright > car3left)) {
                        cancel();
                        //coun.cancel();
                        Chasefail=1;    plCar.setVisibility(View.GONE);

                        Intent intent = new Intent(RoadChase.this, LevelFail.class);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onFinish() {
                secondroundx= plCarleft-(TurnLeft*40)+(TurnRight*40);
                secondroundy= plCarRight+(TurnRight*40)-(TurnLeft*40);
                im4.setVisibility(View.GONE);   im5.setVisibility(View.GONE);   im6.setVisibility(View.GONE);
                TurnLeft=0; TurnRight=0;    cou3.start();
            }
        };

        cou3 = new CountDownTimer(9000, 500) {
            @Override
            public void onTick(long l) {
                im7.setVisibility(View.VISIBLE);    im8.setVisibility(View.VISIBLE);    im9.setVisibility(View.VISIBLE);

                car1left = im7.getLeft();  car1right = im7.getRight(); car1Bottom = (int)im7.getY();
                car2left = im8.getLeft();  car2right = im8.getRight();
                car3left = im9.getLeft();  car3right = im9.getRight();

                plCarleft = secondroundx; plCarRight = secondroundy; plCarTop = plCar.getTop(); finalplcarleft=0; finalplcarright=0;

                if (im7.getY() < 750) im7.setY(im7.getY() + 45);    if (im8.getY() < 750) im8.setY(im8.getY() + 45);
                if (im9.getY() < 750) im9.setY(im9.getY() + 45);

                car1Bottom+=45;

                if(plCarTop<=car1Bottom) {

                    finalplcarleft = plCarleft-(TurnLeft*40)+(TurnRight*40);
                    finalplcarright = plCarRight+(TurnRight*40)-(TurnLeft*40);

                    if ((finalplcarleft < car1right) || ((finalplcarleft < car2left) && (finalplcarright>car2left))
                            || ((finalplcarright > car2left) && (finalplcarleft<car2right)) || (finalplcarright > car3left)) {

                        mpChase.stop();

                        cancel();
                       // coun.cancel();
                        Chasefail=1;    plCar.setVisibility(View.GONE);

                       Intent intent = new Intent(RoadChase.this, LevelFail.class);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onFinish() {
                im7.setVisibility(View.GONE);   im8.setVisibility(View.GONE);   im9.setVisibility(View.GONE);
                thirdroundx= plCarleft-(TurnLeft*40)+(TurnRight*40);
                thirdroundy= plCarRight+(TurnRight*40)-(TurnLeft*40);
                TurnLeft=0; TurnRight=0;
                cou4.start();
            }
        };

        cou4 = new CountDownTimer(9000, 500) {
            @Override
            public void onTick(long l) {
                im10.setVisibility(View.VISIBLE);   im11.setVisibility(View.VISIBLE);
                im12.setVisibility(View.VISIBLE);   im13.setVisibility(View.VISIBLE);

                car1left = im10.getLeft();  car1right = im10.getRight(); car1Bottom = (int)im10.getY();
                car2left = im11.getLeft();  car2right = im11.getRight();
                car3left = im12.getLeft();  car3right = im12.getRight();
                car4left = im13.getLeft();  car4right = im13.getRight();

                plCarleft = thirdroundx; plCarRight = thirdroundy; plCarTop = plCar.getTop();   finalplcarleft=0; finalplcarright=0;

                if (im10.getY() < 750) im10.setY(im10.getY() + 45); if (im11.getY() < 750) im11.setY(im11.getY() + 45);
                if (im12.getY() < 750) im12.setY(im12.getY() + 45); if (im13.getY() < 750) im13.setY(im13.getY() + 45);

                car1Bottom+=45;

                if(plCarTop<=car1Bottom) {

                    finalplcarleft = plCarleft-(TurnLeft*40)+(TurnRight*40);
                    finalplcarright = plCarRight+(TurnRight*40)-(TurnLeft*40);

                    if ((finalplcarleft < car1right) || ((finalplcarleft < car2left) && (finalplcarright>car2left))
                            || ((finalplcarright > car2left) && (finalplcarleft<car2right)) ||
                            ((finalplcarleft < car3left) && (finalplcarright>car3left))
                            || (finalplcarright > car4left)) {

                        mpChase.stop();

                       // coun.cancel();

                        cancel();   Chasefail=1;    plCar.setVisibility(View.GONE);

                        Intent intent = new Intent(RoadChase.this, LevelFail.class);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onFinish() {
                im10.setVisibility(View.GONE);  im11.setVisibility(View.GONE);  im12.setVisibility(View.GONE);
                im13.setVisibility(View.GONE);  TurnLeft=0;  TurnLeft=0;
            }
        };

        CountDownTimer cou1 = new CountDownTimer(9000, 500) {
            @Override
            public void onTick(long l) {
                im1.setVisibility(View.VISIBLE);    im2.setVisibility(View.VISIBLE);    im3.setVisibility(View.VISIBLE);

                car1left = im1.getLeft();  car1right = im1.getRight(); car1Bottom = (int)im1.getY();
                car2left = im2.getLeft();  car2right = im2.getRight();  car3left = im3.getLeft();  car3right = im3.getRight();

                plCarleft = plCar.getLeft(); plCarRight = plCar.getRight(); plCarTop = plCar.getTop();

                finalplcarleft=0; finalplcarright=0;

                if (im1.getY() < 750) im1.setY(im1.getY() + 45);    if (im2.getY() < 750) im2.setY(im2.getY() + 45);
                if (im3.getY() < 750) im3.setY(im3.getY() + 45);

                car1Bottom+=45;

                if(plCarTop<=car1Bottom) {

                    finalplcarleft = plCarleft-(TurnLeft*40)+(TurnRight*40);
                    finalplcarright = plCarRight+(TurnRight*40)-(TurnLeft*40);

                    if ((finalplcarleft < car1right) || ((finalplcarleft < car2right) && (finalplcarright>car2left)) ||
                            ((finalplcarright > car2left) && (finalplcarleft<car2right)) || (finalplcarright > car3left)) {

                        cancel();   Chasefail = 1;  plCar.setVisibility(View.GONE);


                        //coun.cancel();
                        Intent intent = new Intent(RoadChase.this, LevelFail.class);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onFinish() {
                firstroundx= plCarleft-(TurnLeft*40)+(TurnRight*40);
                firstroundy= plCarRight+(TurnRight*40)-(TurnLeft*40);
                im1.setVisibility(View.GONE); im2.setVisibility(View.GONE); im3.setVisibility(View.GONE);
                TurnLeft=0; TurnRight=0;
                cou2.start();
            }
        }.start();

        Button btnLeft = (Button) findViewById(R.id.left);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(plCar.getX()>70) {
                    plCar.setX(plCar.getX()-40);
                    TurnLeft++;
                }
            }});

        Button btnRight = (Button) findViewById(R.id.right);

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(plCar.getX()<1500)    {
                    plCar.setX(plCar.getX()+40);
                    TurnRight++;
                }
            }});
    }
}
