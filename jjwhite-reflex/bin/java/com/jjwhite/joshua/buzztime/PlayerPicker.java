package com.jjwhite.joshua.buzztime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

public class PlayerPicker extends AppCompatActivity {


    //Declare Variables
    NumberPicker numPick = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_picker);

        //Number Picker setup
        numPick = (NumberPicker) findViewById(R.id.player_picker);
        numPick.setMaxValue(4);
        numPick.setMinValue(2);
        numPick.setWrapSelectorWheel(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_picker, menu);
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


    // Called when the user clicks the player_picker_button to confirm player amount
    public void confirmPlayers(View view) {
        Intent intent = new Intent(this, PlayWithFriends.class);
        NumberPicker numPick = (NumberPicker) findViewById(R.id.player_picker);
        intent.putExtra("com.jjwhite.joshua.NUM_PLAYERS", numPick.getValue());
                startActivity(intent);

    }

}
