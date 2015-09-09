package com.jjwhite.joshua.buzztime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ModeSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mode_select, menu);
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

    public void reactTest(View view) {
        // Triggered when the user selects "Test Your Reaction Time" from the Mode Select
        // It will trigger the
        Intent intent = new Intent(this, ReactionTest.class);
        startActivity(intent);
    }

    public void PlayWithFriends(View view) {
        // Triggered when the user selects "Play with Friends" from the Mode Select
        // It will trigger the mode for 2-4 player play
        Intent intent = new Intent(this, PlayerPicker.class);
        startActivity(intent);
    }

    public void records(View view) {
        // Triggered when the user selects "Records" from the menu
        // Will trigger page that displays records
        Intent intent = new Intent(this, Records.class);
        startActivity(intent);
    }

}
