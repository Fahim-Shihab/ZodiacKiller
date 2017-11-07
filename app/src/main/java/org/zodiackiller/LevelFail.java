package org.zodiackiller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LevelFail extends AppCompatActivity {

    MediaPlayer mp;
    static int failure = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_fail);

        failure++;
        MainActivity.mp.stop();
        Scene1a.mpScene.stop();
        KillerFacePuzzle.mpKiller.stop();

        mp = MediaPlayer.create(this,R.raw.bomb);

        Button btnYes = (Button) findViewById(R.id.Yes);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                if(KillerFacePuzzle.Solved == 0){
                    Intent intent = new Intent(LevelFail.this,Scene1a.class);
                    startActivity(intent);
                }
                else if(RoadChase.Chasefail==1){
                    Intent intent = new Intent(LevelFail.this, RoadChase.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(LevelFail.this, Scene1a.class);
                    startActivity(intent);
                }
        }});

        Button btnNo = (Button) findViewById(R.id.No);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();

                Intent intent = new Intent(LevelFail.this, MainActivity.class);
                startActivity(intent);
            }});
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(LevelFail.this, MainActivity.class);
        startActivity(intent);
    }
}
