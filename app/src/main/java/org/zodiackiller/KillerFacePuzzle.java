package org.zodiackiller;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import org.zodiackiller.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.Random;

public class KillerFacePuzzle extends AppCompatActivity {

    private static GestureDetectGridView mGridView;

    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;
    private static int mColumnWidth, mColumnHeight;
    public static int Solved=0;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";
    DatabaseHelper myDb;
    static int pointPuzzle, points, played=0;

    private static String[] tileList;
    static MediaPlayer mpKiller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_killer_face_puzzle);
        Scene1a.mpScene.stop();
        myDb = new DatabaseHelper(this);
        mpKiller = MediaPlayer.create(this, R.raw.cree);

        if(played==0)
        {
            played++;
            mpKiller.start();
        }

        //pointPuzzle = Scene1a.Score;

        Toast.makeText(this, "Solve the puzzle under 45 seconds!!", Toast.LENGTH_SHORT).show();

        CountDownTimer coun =  new CountDownTimer(45000, 1000) {

            public void onTick(long millisUntilFinished) {
                if(Solved==1)
                {
                        points = (int) millisUntilFinished;
                        points = points/50;
                        HighScore.point += points;
                        boolean isInserted = myDb.insertData(HighScore.name,HighScore.score);
                        cancel();
                        //finishAffinity();
                        Intent intent = new Intent(KillerFacePuzzle.this, RoadChase.class);
                        startActivity(intent);
                }
            }
            public void onFinish() {
                    //cancel();
                if(Solved==0) {
                    cancel();

                    HighScore.score = "" + HighScore.point;
                    boolean isInserted = myDb.insertData(HighScore.name, HighScore.score);
                    Intent intent = new Intent(KillerFacePuzzle.this, LevelFail.class);
                    startActivity(intent);
                    //finish();
                }

            }
        }.start();

        init();

        scramble();

        setDimensions();
    }

    //setting the correct position for all the elements of the puzzle

    private void init() {
        mGridView = (GestureDetectGridView) findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);
        }
    }

    //scrambling the puzzle components differently each time

    private void scramble() {
        int index;
        String temp;
        Random rand = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = rand.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display(getApplicationContext());
                if(isSolved())
                {
                    Intent intent = new Intent(KillerFacePuzzle.this, RoadChase.class);
                    startActivity(intent);
                    //finish();
                }
            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(R.drawable.img0);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(R.drawable.img1);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(R.drawable.img2);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(R.drawable.img3);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(R.drawable.img4);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(R.drawable.img5);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(R.drawable.img6);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(R.drawable.img7);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(R.drawable.img8);

            buttons.add(button);
        }

        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        if (isSolved()) {
            Solved = 1;
            Toast.makeText(context, "Well Done"+"\nYou now know who the killer is", Toast.LENGTH_SHORT).show();
        }
    }

    public static void moveTiles(Context context, String direction, int position) {

            // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
        }
        // Upper-center tile
        else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
        }
        // Upper-right-corner tile
        else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
        }
        // Left-side tiles
        else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
        }
        // Right-side AND bottom-right-corner tiles
        else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }
    }

    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(KillerFacePuzzle.this, MainActivity.class);
        startActivity(intent);
    }
}
