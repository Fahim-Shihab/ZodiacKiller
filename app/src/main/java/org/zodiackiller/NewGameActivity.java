package org.zodiackiller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class NewGameActivity extends AppCompatActivity {

    private int skip = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        //playing the game intro video

        final VideoView mVideoView2 = (VideoView)findViewById(R.id.videoView);
        String uriPath2 = "android.resource://"+getPackageName()+"/"+R.raw.intro;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);

        mVideoView2.start();

        CountDownTimer coun =  new CountDownTimer(22000, 1000) {

            public void onTick(long millisUntilFinished) {
                if(skip==1)    cancel();
            }
            public void onFinish() {
                if(skip==0) {

                    Intent intent = new Intent(NewGameActivity.this, Scene1a.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();

        //skipping

        Button btnnext = (Button) (findViewById(R.id.next));
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip = 1;
                mVideoView2.stopPlayback();
                finish();
                Intent intent = new Intent(NewGameActivity.this, Scene1a.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
