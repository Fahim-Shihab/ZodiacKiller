package org.zodiackiller;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import org.zodiackiller.db.DatabaseHelper;

public class Puzzletwo extends AppCompatActivity {
    Button r, i, v, e, rr, s, ii, d, ee,reset;
    String result;
    TextView tv ;
    DatabaseHelper myDb;
    CountDownTimer cn =  new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {
            tv.setText("" + millisUntilFinished / 1000);
            //retime =  millisUntilFinished / 1000;
        }

        public void onFinish() {

            tv.setText("over!");
            GameOverDialogue();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzletwo);
        myDb = new DatabaseHelper(this);
        result = "";
        r = (Button)findViewById(R.id.button39);
        i = (Button)findViewById(R.id.button38);
        v = (Button)findViewById(R.id.button37);
        e = (Button)findViewById(R.id.button36);
        rr = (Button)findViewById(R.id.button35);
        s = (Button)findViewById(R.id.button34);
        ii = (Button)findViewById(R.id.button33);
        d = (Button)findViewById(R.id.button32);
        ee = (Button)findViewById(R.id.button31);
        reset = (Button)findViewById(R.id.resetButton);

        tv = (TextView)findViewById(R.id.puzzletime);
        cn.start();

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result+  "R";
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result.concat("I");
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {

                v.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result.concat("V");
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                e.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result.concat("E");
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });

        rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rr.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result.concat("R");
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result.concat("S");
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });

        ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ii.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result.concat("I");
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                d.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result.concat("D");
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });

        ee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ee.setTextColor(getResources().getColor(R.color.colorAccent));
                result = result.concat("E");
                if(result.equals("RIVERSIDE")) {
                    SceneClearDialogue();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Reset();
            }
        });



    }
    //If Puzzle is Solved
    private void SceneClearDialogue() {
        HighScore.point += 100;
        int sc = Integer.parseInt(tv.getText().toString());
        HighScore.point+=sc;
        cn.cancel();
        Toast.makeText(Puzzletwo.this,"Congratulations! Puzzle Solved!!", Toast.LENGTH_LONG).show();
        Intent sc3 = new Intent(Puzzletwo.this,KillerFacePuzzle.class);
        startActivity(sc3);
        finish();
    }

    //If the game is over
    private void GameOverDialogue() {
        HighScore.score =""+HighScore.point;
        boolean isInserted = myDb.insertData(HighScore.name,HighScore.score);
        /*Intent intent = new Intent(Puzzletwo.this, LevelFail.class);
        startActivity(intent);*/
        finish();
    }
    //Reset the user input
    private void Reset(){
        result = "";
        r.setTextColor(getResources().getColor(R.color.colorBlack));
        i.setTextColor(getResources().getColor(R.color.colorBlack));
        v.setTextColor(getResources().getColor(R.color.colorBlack));
        e.setTextColor(getResources().getColor(R.color.colorBlack));
        rr.setTextColor(getResources().getColor(R.color.colorBlack));
        s.setTextColor(getResources().getColor(R.color.colorBlack));
        ii.setTextColor(getResources().getColor(R.color.colorBlack));
        d.setTextColor(getResources().getColor(R.color.colorBlack));
        ee.setTextColor(getResources().getColor(R.color.colorBlack));
    }

}
