package com.jjwhite.joshua.buzztime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.os.Handler;

import java.util.Date;
import java.util.Random;

public class ReactionTest extends AppCompatActivity {

    LinearLayout single_player;
    Button single_player_button;
    AlertDialog.Builder alert = null;
    private Handler gamehandle;
    int TOOEARLY;
    int CALCLATENCY;
    int PLAYGAME;
    Message msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_test);
        single_player_button = (Button) findViewById(R.id.single_player_button);

        TOOEARLY = 1;
        CALCLATENCY = 2;
        PLAYGAME = 3;
        rand = new Random();

        single_player_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if ((SystemClock.elapsedRealtime() - lastTime < randomdelay)) {
                    Message msg = new Message();
                    msg.what = TOOEARLY;
                    gamehandle.sendMessage(msg);
                }
                else {

                    Message msg = new Message();
                    msg.what = CALCLATENCY;
                    gamehandle.sendMessage(msg);
                }
            }
        });


         gamehandle = new Handler(){

             public void handleMessage(Message msg) {
                 if(msg.what == TOOEARLY) {

                     single_player_button.setText("TOO EARLY");
                     testGame();
                 }
                 else if(msg.what == CALCLATENCY) {
                     double latency = calcLatency(lastTime,randomdelay);
                     single_player_button.setText(Double.toString(latency));
                     testGame();
                 }
                 else if(msg.what == PLAYGAME) {
                     single_player_button.setText("CLICK!");
                 }
             }
         };
    }


    @Override
    protected void onStart() {
        super.onStart();
        alert();
        testGame();}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void alert(){
        alert = new AlertDialog.Builder(ReactionTest.this);
        alert.setTitle("Time to test your reaction time");
        alert.setMessage("A message to click the button will randomly appear.  Click the button as soon as you are propmted");
        alert.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        alert.create();
        alert.show();
    }

   public void testGame() {

       msg = new Message();
       msg.what = PLAYGAME;
       lastTime = SystemClock.elapsedRealtime();
       gamehandle.sendMessageDelayed(msg, randomdelay.longValue());




   }


}


