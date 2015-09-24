package com.jjwhite.joshua.buzztime;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    AlertDialog.Builder alert = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        single_player_button = (Button) findViewById(R.id.single_player_button);
        setContentView(R.layout.activity_reaction_test);
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

    public long latencyOnClick() throws InterruptedException {
        Random rand = new Random();
        wait((Double.doubleToLongBits(rand.nextDouble()) * 2010) - 10 );
        Date date = new Date();
        long time = date.getTime();
        return time;

    }

    public void alert(){
        alert = new AlertDialog.Builder(ReactionTest.this);
        alert.setTitle("Time to test your reaction time");
        alert.setMessage("A message to click the button will randomly appear.  Click the button as soon as you are propmted");
        alert.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              //  testGame();
            }
        });

        alert.create();
        alert.show();
    }

    public void testGame() throws InterruptedException{

        Random rand = new Random();
        wait((Double.doubleToLongBits(rand.nextDouble()) * 2010) - 10 );


    }
}
