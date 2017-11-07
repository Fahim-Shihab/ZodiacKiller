package org.zodiackiller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.zodiackiller.db.DatabaseHelper;

import java.util.concurrent.TimeUnit;

public class PuzzleThree extends AppCompatActivity{

    private PopupWindow pwindo;
    private RelativeLayout re;
    static int solvedPuzzle3 = 0;
    TextView _tv;
    private int count=0,a=0,b=0,c=0,d=0,e=0,f=0;
    public static int failure = 1;
    static long points = 0;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_three);
        myDb = new DatabaseHelper(this);
        Button btn1 = (Button) findViewById(R.id.firM);
        Button btn2 = (Button) findViewById(R.id.firI);
        Button btn3 = (Button) findViewById(R.id.firR);
        Button btn4 = (Button) findViewById(R.id.firP);
        Button btn5 = (Button) findViewById(R.id.firU);
        Button btn6 = (Button) findViewById(R.id.lastR);
        Button btn7 = (Button) findViewById(R.id.lastA);

        _tv = (TextView) findViewById( R.id.textView1 );

        CountDownTimer coun =  new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                _tv.setText(String.format("%d sec(s)",
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                long timeleft = millisUntilFinished;
                if(f==1)    {
                    solvedPuzzle3 = 1;
                    cancel();
                    HighScore.point += timeleft/50;
                    finish();
                }
            }
            public void onFinish() {
                if(f==0) {
                    failure = 0;
                    _tv.setText("Time Finished");
                    HighScore.score =""+HighScore.point;
                    boolean isInserted = myDb.insertData(HighScore.name,HighScore.score);
                    Intent intent = new Intent(PuzzleThree.this, LevelFail.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e=1;
            }});
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e==1)    a=1;
            }});
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a==1)    b=1;
            }});
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b==1)    c=1;
            }});
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c==1)    d=1;
            }});
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(d==1)    f=1;
            }});
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f==1)    pops();
            }});
    }
    public void pops()
    {
        try {
            re = (RelativeLayout) findViewById(R.id.puzzlethree);

            LayoutInflater inflater = (LayoutInflater) PuzzleThree.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.puzzlesolved,null);
            pwindo= new PopupWindow(layout, 1000, 400, true);
            pwindo.showAtLocation(re, Gravity.NO_GRAVITY, 200, 200);

            layout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent mo) {
                    pwindo.dismiss();
                    Intent intent = new Intent(PuzzleThree.this, Scene1a.class);
                    startActivity(intent);
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(PuzzleThree.this, "The countdown will still continue, solve the puzzle first", Toast.LENGTH_SHORT).show();
    }
}
