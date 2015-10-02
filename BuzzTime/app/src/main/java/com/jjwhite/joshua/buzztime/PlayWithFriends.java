package com.jjwhite.joshua.buzztime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayWithFriends extends AppCompatActivity {

    RecordDealer multiplayer_records;
    AlertDialog.Builder alert = null;
    private long lastClickTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int numOfPlayers = getNumOfPlayers();

        setContentView(R.layout.activity_play_with_friends);
        formatPlayerButtons(numOfPlayers);

        lastClickTime = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_with_friends, menu);
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


    public void formatPlayerButtons(int numOfPlayers){
        Button button3 = (Button)findViewById(R.id.button_player_3);
        Button button4 = (Button)findViewById(R.id.button_player_4);

        if(numOfPlayers == 2){
            button3.setVisibility(View.GONE);
            button4.setVisibility(View.GONE);
        }
        if(numOfPlayers == 3){
            button4.setVisibility(View.GONE);
        }
    }

    public void buzzIn(View view) throws InterruptedException {

            if(!(SystemClock.elapsedRealtime() - lastClickTime < 1500)){

               lastClickTime = SystemClock.elapsedRealtime();

                String playerNum = getResources().getResourceEntryName(view.getId()).substring(14);
                multiplayer_records = new RecordDealer();
                multiplayer_records.incrimentStats(this, playerNum, getNumOfPlayers());
                //delay to avoid a late player from dismissing the alert
                alertPlayer(playerNum);
                Thread.sleep(500, 0);
    }
   }

    public void alertPlayer(String playernum){
        alert = new AlertDialog.Builder(PlayWithFriends.this);
        alert.setTitle("Buzzed In");
        alert.setMessage("Player " + playernum + " Buzzed In First! " );
        alert.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.create();
        alert.show();
    }

    public int getNumOfPlayers(){
        Intent intent = getIntent();
        return intent.getIntExtra("com.jjwhite.joshua.NUM_PLAYERS", 2);
    }


}
