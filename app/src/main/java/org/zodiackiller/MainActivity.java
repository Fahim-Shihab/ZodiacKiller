package org.zodiackiller;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private int currentIndex=-1, fxindex=-1, counter;
    static int times = 0;
    static int music = 0;
    private TextSwitcher mSwitcher, nSwitcher;
    Button btnNext, btnex, btnnew, btnContinue;
    static MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //controlling the background music

        btnNext=(Button)findViewById(R.id.music);
        mSwitcher = (TextSwitcher) findViewById(R.id.muse);

        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                TextView myText = new TextView(MainActivity.this);
                myText.setTextSize(20);
                myText.setTextColor(Color.WHITE);
                return myText;
            }
        });

            mp = MediaPlayer.create(this, R.raw.creep);
        if(music==0) {
            mp.start();
            mp.setLooping(true);
            music++;
        }

        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);

        mSwitcher.setText("On");

        btnNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                currentIndex++;

                if(currentIndex%2==0)
                {
                    mSwitcher.setText("Off");
                    mp.pause();
                }

                else
                {
                    mSwitcher.setText("On");
                    mp.start();
                }
            }
        });

        btnex = (Button)(findViewById(R.id.Exit));
        btnex.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mp.stop();
                finishAffinity();
                System.exit(0);
            }
        });

        btnnew = (Button)(findViewById(R.id.NewGame));
        btnnew.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerName.class);
                startActivity(intent);
            }
        });

        btnContinue = (Button) (findViewById(R.id.high));

        btnContinue.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, hScore.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        mp.stop();
        finishAffinity();
        System.exit(0);
    }
}
