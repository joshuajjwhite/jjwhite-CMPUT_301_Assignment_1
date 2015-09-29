package com.jjwhite.joshua.buzztime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Message;
import android.os.SystemClock;
import android.provider.ContactsContract;
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

    private LinearLayout single_player;
    private Button single_player_button;
    private AlertDialog.Builder alert = null;
    private Handler gamehandle;
    private Message msg;
    private DataManager datamanager;
    private int START;
    private int PLAYGAME;
    private int POSTCLICK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_test);
        msg = new Message();
        START = 1;
        PLAYGAME = 2;
        POSTCLICK = 3;

        single_player_button = (Button) findViewById(R.id.single_player_button);

        datamanager = DataManager.getInstance(this);


        single_player_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                msg = new Message();
                msg.what = POSTCLICK;
                gamehandle.sendMessage(msg);
            }
        });


         gamehandle = new Handler(){

             public void handleMessage(Message msg) {
                 if(msg.what == START) {
                     datamanager.changeState("START");
                     datamanager.gamePrep();
                     gameStart();

                 }
                 else if(msg.what == PLAYGAME) {
                     String readystate = datamanager.readyTest();
                     if(readystate.equals("CLICK!")){
                        single_player_button.setText(readystate);}
                     else{single_player_button.setText("");
                         msg = new Message();
                         msg.what = START;
                         gamehandle.sendMessage(msg);}
                 }

                 else if(msg.what == POSTCLICK){
                     gamehandle.removeMessages(PLAYGAME);
                     single_player_button.setText(datamanager.handleClick());
                     datamanager.changeState("WAIT");
                     msg = new Message();
                     msg.what = START;
                     gamehandle.sendMessage(msg);

                 }
             }
         };
    }


    @Override
    protected void onStart() {
        super.onStart();
        alert();
        }

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
                afterDismiss();
            }
        });

        alert.create();
        alert.show();

    }

    public void afterDismiss(){
    msg.what = START;
    gamehandle.sendMessage(msg);}

   public void gameStart() {

       msg = new Message();
       msg.what = PLAYGAME;
       gamehandle.sendMessageDelayed(msg, datamanager.getRandomDelay());




   }


}


