package org.zodiackiller;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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

import java.util.Random;

public class Scene1a extends AppCompatActivity{

    private PopupWindow pwindo;
    private RelativeLayout re;
    private TextView v1,v2,v3,v4,v5,v6,v7;

    static MediaPlayer mpScene;
    private static int ThingsChecked = 7;
    public static int Score=0, played = 0;
    private static int MirrorCheck = 0, DoorNoteCheck = 0, ToolCheck=0,DrawerCheck=0, SuitcaseCheck = 0, NoteClue=0, WindowCheck=0;

    CountDownTimer SuitcaseBomb = new CountDownTimer(15000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }
        @Override
        public void onFinish() {
            finish();
            Intent inf = new Intent(Scene1a.this,LevelFail.class);
            startActivity(inf);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene1a);

        MainActivity.mp.stop();

        mpScene = MediaPlayer.create(this, R.raw.cree);

        if(played==0){
            played++;
            mpScene.start();
        }

        v1 = (TextView) findViewById(R.id.ToolView);
        v2 = (TextView) findViewById(R.id.mirr);
        v3 = (TextView) findViewById(R.id.Note1);
        v4 = (TextView) findViewById(R.id.Window);
        v5 = (TextView) findViewById(R.id.Draw);
        v6 = (TextView) findViewById(R.id.Note2);
        v7 = (TextView) findViewById(R.id.brie);

        if(ToolCheck==1)  v1.setVisibility(View.INVISIBLE);
        if(NoteClue==1)  v3.setVisibility(View.INVISIBLE);
        if(SuitcaseCheck==1)  {
            v7.setVisibility(View.INVISIBLE);

            HighScore.point += 50;

        }
        if(WindowCheck==1)  v4.setVisibility(View.INVISIBLE);
        if(DrawerCheck==1)  v5.setVisibility(View.INVISIBLE);
        if(MirrorCheck==1)  v2.setVisibility(View.INVISIBLE);
        if(DoorNoteCheck==1)  v6.setVisibility(View.INVISIBLE);

        Button btn1 = (Button) findViewById(R.id.tool);

        btn1.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
            if(ToolCheck==0){
                HighScore.point +=30;
                ToolCheck=1;
                ThingsChecked--;
            }
            v1.setVisibility(View.INVISIBLE);
            }});


    Button btn2 = (Button) findViewById(R.id.mirror);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MirrorCheck==0) {
                    HighScore.point +=30;
                    MirrorCheck=1;
                    ThingsChecked--;
                }
                v2.setVisibility(View.INVISIBLE);
            }
        });

        //setting the clue for the puzzle

    Button btn3 = (Button) findViewById(R.id.note);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NoteClue==0) {
                    HighScore.point +=30;
                    NoteClue=1;
                    ThingsChecked--;
                    Toast.makeText(Scene1a.this, "Benicia" +"\nVallejo"+"\nGaviton"+"\nWhat does this mean?", Toast.LENGTH_LONG).show();
                }
                v3.setVisibility(View.INVISIBLE);

            }
        });

        Button btnWind = (Button) findViewById(R.id.window);

        btnWind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(WindowCheck==0) {
                    HighScore.point +=30;
                    WindowCheck=1;
                    ThingsChecked--;
                }
                v4.setVisibility(View.INVISIBLE);
            }
        });

        Button btnDrawer = (Button) findViewById(R.id.drawer);

        btnDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DrawerCheck==0) {
                    HighScore.point +=30;
                    DrawerCheck=1;
                    ThingsChecked--;
                }
                v5.setVisibility(View.INVISIBLE);
            }
        });

        Button btnPaper = (Button) findViewById(R.id.paper);

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DoorNoteCheck==0) {
                    HighScore.point +=30;
                    DoorNoteCheck=1;
                    ThingsChecked--;
                }
                v6.setVisibility(View.INVISIBLE);
            }
        });

        Button btn4 = (Button) findViewById(R.id.suitcase);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NoteClue==1) {
                    v7.setVisibility(View.INVISIBLE);
                    if (SuitcaseCheck == 0) {
                        HighScore.point +=30;
                        SuitcaseCheck = 1;
                        ThingsChecked--;
                        SuitcaseBomb.start();
                        try {
                            re = (RelativeLayout) findViewById(R.id.repo);

                            LayoutInflater inflater = (LayoutInflater) Scene1a.this
                                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.popup, null);
                            pwindo = new PopupWindow(layout, 1000, 400, true);
                            pwindo.showAtLocation(re, Gravity.NO_GRAVITY, 760, 540);

                            layout.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View view, MotionEvent mo) {
                                    SuitcaseBomb.cancel();
                                    pwindo.dismiss();

                                    if (ThingsChecked % 7 == 1|| ThingsChecked % 7 == 3) {
                                        Intent intent = new Intent(Scene1a.this, PuzzleOne.class);
                                        startActivity(intent);
                                    } else if (ThingsChecked % 7 == 2 || ThingsChecked % 7 == 6) {
                                        Intent intent = new Intent(Scene1a.this, PuzzleTwo.class);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(Scene1a.this, PuzzleThree.class);
                                        startActivity(intent);
                                    }
                                    return true;
                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(Scene1a.this, "Sorry, try again", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if(PuzzleOne.solvedPuzzle1==1||PuzzleTwo.solvedPuzzle2==1||PuzzleThree.solvedPuzzle3==1)
                        Toast.makeText(Scene1a.this, "Suitcase containing bomb; neutralized", Toast.LENGTH_SHORT).show();
                        else{
                            int p;
                            Random rand = new Random();
                            p = rand.nextInt(8);
                            if (p % 7 == 1|| p % 7 == 3) {
                                Intent intent = new Intent(Scene1a.this, PuzzleOne.class);
                                startActivity(intent);
                            } else if (p % 7 == 2 || p % 7 == 6) {
                                Intent intent = new Intent(Scene1a.this, PuzzleTwo.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(Scene1a.this, PuzzleThree.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
                else Toast.makeText(Scene1a.this, "Search all the other clues first", Toast.LENGTH_SHORT).show();
            }
        });

        //Leaving the room

        Button btnLeave = (Button) findViewById(R.id.Leave);

        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //If all the things are checked, then the player can leave

                if(ThingsChecked>0) Toast.makeText(Scene1a.this, "Search all the things before leaving the room", Toast.LENGTH_SHORT).show();
                else if(PuzzleOne.solvedPuzzle1==1||PuzzleTwo.solvedPuzzle2==1||PuzzleThree.solvedPuzzle3==1)
                {
                    Toast.makeText(Scene1a.this,"Investigation complete",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Scene1a.this, scenetwo.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Scene1a.this, MainActivity.class);
        startActivity(intent);
    }
}