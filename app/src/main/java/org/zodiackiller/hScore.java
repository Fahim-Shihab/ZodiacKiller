package org.zodiackiller;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.zodiackiller.db.DatabaseHelper;

public class hScore extends AppCompatActivity {
    DatabaseHelper myDb;
    int count;
    TextView name1,name2,name3,name4,name5,score1,score2,score3,score4,score5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_score);
        myDb = new DatabaseHelper(this);
        count = 1;
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        name4 = (TextView) findViewById(R.id.name4);
        name5 = (TextView) findViewById(R.id.name5);
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        score3 = (TextView) findViewById(R.id.score3);
        score4 = (TextView) findViewById(R.id.score4);
        score5 = (TextView) findViewById(R.id.score5);

        setHighScore();

    }

    void setHighScore(){
        Cursor res = myDb.getAllData();
        while (res.moveToNext()) {
            if(count>5) break;
            if(count == 1){
                name1.setText(res.getString(1));
                score1.setText(res.getString(2));
            }
            else if(count == 2){
                name2.setText(res.getString(1));
                score2.setText(res.getString(2));
            }
            else if(count == 3){
                name3.setText(res.getString(1));
                score3.setText(res.getString(2));
            }
            else if(count == 4){
                name4.setText(res.getString(1));
                score4.setText(res.getString(2));
            }
            else if(count == 5){
                name5.setText(res.getString(1));
                score5.setText(res.getString(2));
            }
            count++;
        }

    }
}
