package org.zodiackiller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.zodiackiller.db.DatabaseHelper;

import java.util.Random;

public class scenetwo extends AppCompatActivity {
    int key = 5, cnt = 0;
    TextView clue1, clue2, clue3, clue4, clue5, clue6;
    TextView time;
    ImageButton bag, cap, card, cd, chain, diary, dirt, laptop, range,cdbackpart;
    DatabaseHelper myDb ;
    static MediaPlayer mpr;
    CountDownTimer n = new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {
            time.setText("" + millisUntilFinished / 1000);
        }

        public void onFinish() {

            time.setText("over!");
            GameOverDialogue();
        }
    }.start();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenetwo);
        mpr = MediaPlayer.create(this, R.raw.cre);
        mpr.start();
        final String clues[] = {"BAG", "CAP", "CARD", "CD", "CHAIN", "DIARY", "DIRT", "LAPTOP", "RANGE", "CD BACKPART"};
        final int length = clues.length;
        myDb = new DatabaseHelper(this);
        time = (TextView)findViewById(R.id.time);
        clue1 = (TextView)findViewById(R.id.clue1);
        clue2 = (TextView)findViewById(R.id.clue2);
        clue3 = (TextView)findViewById(R.id.clue3);
        clue4 = (TextView)findViewById(R.id.clue4);
        clue5 = (TextView)findViewById(R.id.clue5);
        clue6 = (TextView)findViewById(R.id.clue6);
        bag = (ImageButton)findViewById(R.id.clue_bag);
        cap = (ImageButton)findViewById(R.id.clue_cap);
        card = (ImageButton)findViewById(R.id.clue_card);
        cd = (ImageButton)findViewById(R.id.clue_cd);
        chain = (ImageButton)findViewById(R.id.clue_chain);
        diary = (ImageButton)findViewById(R.id.clue_diary);
        dirt = (ImageButton)findViewById(R.id.clue_dirt);
        laptop = (ImageButton)findViewById(R.id.clue_laptop);
        range = (ImageButton)findViewById(R.id.clue_range);
        cdbackpart = (ImageButton)findViewById(R.id.clue_cd_backpart);
        suffleArray(clues);
        initClick();
        initClue(clues);


        //Timer function to set the timer

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snap);


        range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                range.setVisibility(View.INVISIBLE);
                updateClue("RANGE", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();


            }
        });

        cdbackpart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                cdbackpart.setVisibility(View.INVISIBLE);
                updateClue("CD BACKPART", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();

            }
        });

        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                laptop.setVisibility(View.INVISIBLE);
                //createAndShowAlertDialog();
                updateClue("LAPTOP", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();


            }
        });

        dirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                dirt.setVisibility(View.INVISIBLE);
                updateClue("DIRT", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();


            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                diary.setVisibility(View.INVISIBLE);
                updateClue("DIARY", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();


            }
        });

        chain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                chain.setVisibility(View.INVISIBLE);
                updateClue("CHAIN", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();


            }
        });

        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                cd.setVisibility(View.INVISIBLE);
                updateClue("CD", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();


            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                card.setVisibility(View.INVISIBLE);
                updateClue("CARD", clues);
                cnt++;
                Toast.makeText(scenetwo.this,"Paul Stine\nRiverside City College, Riverside, Calif.",Toast.LENGTH_LONG).show();
                if(cnt>=length) SceneClearDialogue();


            }
        });

        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                cap.setVisibility(View.INVISIBLE);
                updateClue("CAP", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();


            }
        });

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                HighScore.point += 30;
                bag.setVisibility(View.INVISIBLE);
                updateClue("BAG", clues);
                cnt++;
                if(cnt>=length) SceneClearDialogue();


            }
        });


    }


    //Shuffle array method for random clue
    public static void suffleArray(String array[]){
        int i;
        int index;
        String temp;
        Random random = new Random();
        for( i=1;i<array.length;i++)
        {
            index = random.nextInt(i+1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    //Set an imagebutton to clickable when clue appears
    public  void settoClickable(String clue){
        if(clue.equals("BAG")) bag.setEnabled(true);
        else if(clue.equals("CAP")) cap.setEnabled(true);
        else if(clue.equals("CARD")) card.setEnabled(true);
        else if(clue.equals("CD")) cd.setEnabled(true);
        else if(clue.equals("CHAIN")) chain.setEnabled(true);
        else if(clue.equals("DIARY")) diary.setEnabled(true);
        else if(clue.equals("DIRT")) dirt.setEnabled(true);
        else if(clue.equals("LAPTOP")) laptop.setEnabled(true);
        else if(clue.equals("RANGE")) range.setEnabled(true);
        else if(clue.equals("CD BACKPART")) cdbackpart.setEnabled(true);
    }

    //Update the clue one by one
    public void updateClue(String s, String array[]){

        if(clue1.getText().equals(s)){
            if(key<9) {
                clue1.setText(array[++key]);
                settoClickable(array[key]);
            }
            else clue1.setVisibility(View.INVISIBLE);
        }
        else if(clue2.getText().equals(s)) {
            if(key<9){
                clue2.setText(array[++key]);
                settoClickable(array[key]);
            }
            else clue2.setVisibility(View.INVISIBLE);
        }
        else if(clue3.getText().equals(s)) {
            if(key<9){
                clue3.setText(array[++key]);
                settoClickable(array[key]);
            }
            else clue3.setVisibility(View.INVISIBLE);
        }
        else if(clue4.getText().equals(s)) {
            if(key<9) {
                clue4.setText(array[++key]);
                settoClickable(array[key]);
            }
            else clue4.setVisibility(View.INVISIBLE);
        }
        else if(clue5.getText().equals(s)){
            if(key<9) {
                clue5.setText(array[++key]);
                settoClickable(array[key]);
            }
            else clue5.setVisibility(View.INVISIBLE);
        }
        else if(clue6.getText().equals(s)) {
            if(key<9) {
                clue6.setText(array[++key]);
                settoClickable(array[key]);
            }
            else clue6.setVisibility(View.INVISIBLE);
        }
    }
    //Initially set all clues unclickable
    public void initClick(){
        chain.setEnabled(false);
        bag.setEnabled(false);
        cap.setEnabled(false);
        card.setEnabled(false);
        cd.setEnabled(false);
        diary.setEnabled(false);
        dirt.setEnabled(false);
        laptop.setEnabled(false);
        range.setEnabled(false);
        cdbackpart.setEnabled(false);
    }

    public void initClue(String clues[]){
        clue1.setText(clues[0]);
        settoClickable(clues[0]);
        clue2.setText(clues[1]);
        settoClickable(clues[1]);
        clue3.setText(clues[2]);
        settoClickable(clues[2]);
        clue4.setText(clues[3]);
        settoClickable(clues[3]);
        clue5.setText(clues[4]);
        settoClickable(clues[4]);
        clue6.setText(clues[5]);
        settoClickable(clues[5]);
    }

    //When all clues solved and puzzle comes
    private void SceneClearDialogue() {
        int tm =Integer.parseInt(time.getText().toString());
        HighScore.point += tm;
        n.cancel();
        Intent intentPuzzle = new Intent(scenetwo.this, Puzzletwo.class);
        startActivity(intentPuzzle);
        finish();
    }
    //If the game is over
    private void GameOverDialogue() {
        HighScore.score = ""+ HighScore.point;
        boolean isInserted = myDb.insertData(HighScore.name,HighScore.score);
        n.cancel();
        /*Intent go = new Intent(scenetwo.this,LevelFail.class);
        startActivity(go);*/
        finish();

    }

}
