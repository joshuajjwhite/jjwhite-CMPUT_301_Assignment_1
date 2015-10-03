package com.jjwhite.joshua.buzztime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MultiplayerRecords extends AppCompatActivity {

    //table text views
    private TextView two_two;
    private TextView two_three;
    private TextView three_two;
    private TextView three_three;
    private TextView three_four;
    private TextView four_two;
    private TextView four_three;
    private TextView four_four;
    private TextView four_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_records);
        two_two = (TextView) findViewById(R.id.multi_2_2);
        two_three = (TextView) findViewById(R.id.multi_2_3);
        three_two = (TextView) findViewById(R.id.multi_3_2);
         three_three = (TextView) findViewById(R.id.multi_3_3);
         three_four = (TextView) findViewById(R.id.multi_3_4);
         four_two = (TextView) findViewById(R.id.multi_4_2);
        four_three = (TextView) findViewById(R.id.multi_4_3);
         four_four = (TextView) findViewById(R.id.multi_4_4);
         four_five = (TextView) findViewById(R.id.multi_4_5);


        setMultiRecords();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiuplayer_records, menu);
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

    public void setMultiRecords(){
        RecordDealer recorddeal = new RecordDealer();
        for(int i = 2; i <= 4; i ++){
            if(i == 2){
                two_two.setText(Integer.toString(recorddeal.loadMultiStats(this, "1", Integer.toString(i))));
                two_three.setText(Integer.toString(recorddeal.loadMultiStats(this,"2", Integer.toString(i))));
            }
            else if(i == 3){
                three_two.setText(Integer.toString(recorddeal.loadMultiStats(this,"1", Integer.toString(i))));
                three_three.setText(Integer.toString(recorddeal.loadMultiStats(this,"2", Integer.toString(i))));
                three_four.setText(Integer.toString(recorddeal.loadMultiStats(this,"3", Integer.toString(i))));
            }

            else{
                four_two.setText(Integer.toString(recorddeal.loadMultiStats(this,"1", Integer.toString(i))));
                four_three.setText(Integer.toString(recorddeal.loadMultiStats(this,"2", Integer.toString(i))));
                four_four.setText(Integer.toString(recorddeal.loadMultiStats(this,"3", Integer.toString(i))));
                four_five.setText(Integer.toString(recorddeal.loadMultiStats(this,"4", Integer.toString(i))));
            }
        }
    }

    public void clearMyMultiStats(View view){
        RecordDealer recorddeal= new RecordDealer();
        recorddeal.clearMultiplayerRecords(this);
        setMultiRecords();
    }

    public void singlePlayerRecords(View view) {
        //Triggered when the user selects "Records" from the menu
        // Will trigger page that displays records
        Intent intent = new Intent(this, SinglePlayerRecords.class);
        startActivity(intent);
    }
}
