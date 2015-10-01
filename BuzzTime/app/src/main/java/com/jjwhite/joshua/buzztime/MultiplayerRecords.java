package com.jjwhite.joshua.buzztime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MultiplayerRecords extends AppCompatActivity {

    private TextView two_one;
    private TextView two_two;
    private TextView three_one;
    private  TextView three_two;
    private TextView three_three;
    private TextView four_one;
    private TextView four_two;
    private TextView four_three;
    private TextView four_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_records);
        two_one = (TextView) findViewById(R.id.multi_2_2);
        two_two = (TextView) findViewById(R.id.multi_2_3);
        three_one = (TextView) findViewById(R.id.multi_3_2);
         three_two = (TextView) findViewById(R.id.multi_3_3);
         three_three = (TextView) findViewById(R.id.multi_3_4);
         four_one = (TextView) findViewById(R.id.multi_4_2);
        four_two = (TextView) findViewById(R.id.multi_4_3);
         four_three = (TextView) findViewById(R.id.multi_4_4);
         four_four = (TextView) findViewById(R.id.multi_4_5);

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
                two_one.setText(Integer.toString(recorddeal.loadMultiStats(this, "1", Integer.toString(i))));
                two_two.setText(Integer.toString(recorddeal.loadMultiStats(this,"2", Integer.toString(i))));
            }
            else if(i == 3){
                three_one.setText(Integer.toString(recorddeal.loadMultiStats(this,"1", Integer.toString(i))));
                three_two.setText(Integer.toString(recorddeal.loadMultiStats(this,"2", Integer.toString(i))));
                three_three.setText(Integer.toString(recorddeal.loadMultiStats(this,"3", Integer.toString(i))));
            }

            else{
                four_one.setText(Integer.toString(recorddeal.loadMultiStats(this,"1", Integer.toString(i))));
                four_two.setText(Integer.toString(recorddeal.loadMultiStats(this,"2", Integer.toString(i))));
                four_three.setText(Integer.toString(recorddeal.loadMultiStats(this,"3", Integer.toString(i))));
                four_four.setText(Integer.toString(recorddeal.loadMultiStats(this,"4", Integer.toString(i))));
            }
        }
    }

    public void clearMyMultiStats(View view){
        RecordDealer recorddeal= new RecordDealer();
        recorddeal.clearMultiplayerRecords(this);
        setMultiRecords();
    }
}
