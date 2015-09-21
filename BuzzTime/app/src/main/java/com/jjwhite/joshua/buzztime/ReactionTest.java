package com.jjwhite.joshua.buzztime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Date;
import java.util.Random;

public class ReactionTest extends AppCompatActivity {

    LinearLayout single_player;
    Button single_player_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_test);

       // single_player_button.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View v) {

           // }
       // });
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

    public long latencyOnClick() throws InterruptedException {
        Random rand = new Random();
        wait((Double.doubleToLongBits(rand.nextDouble()) * 2010) - 10 );
        Date date = new Date();
        long time = date.getTime();
        return time;

    }
}
