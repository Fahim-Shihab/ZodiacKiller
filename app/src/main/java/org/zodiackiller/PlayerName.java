package org.zodiackiller;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.zodiackiller.db.DatabaseHelper;

public class PlayerName extends AppCompatActivity {

    private Button ok;
    public  EditText player;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player_name);
        HighScore.point = 0;
        myDb = new DatabaseHelper(this);
        final MediaPlayer mpr = MediaPlayer.create(this, R.raw.cre);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.click);
        mpr.start(); //Game music start
        mpr.setLooping(true);
        ok = (Button) findViewById(R.id.button_ok);
        player = (EditText) findViewById(R.id.player_name);



        ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HighScore.name = player.getText().toString();
                        Intent intent = new Intent(PlayerName.this, NewGameActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
        );

    }


}
