package com.jjwhite.joshua.buzztime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayWithFriends extends AppCompatActivity {

    int numOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int numOfPlayers = intent.getIntExtra("com.jjwhite.joshua.NUM_PLAYERS",2);
       // TextView textView = new TextView(this);
        //textView.setTextSize(40);
        //textView.setText(Integer.toString(numOfPlayers));
        //setContentView(textView);
        setContentView(R.layout.activity_play_with_friends);
        formatPlayerButons(numOfPlayers);
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


    public void formatPlayerButons(int numOfPlayers){
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

}
