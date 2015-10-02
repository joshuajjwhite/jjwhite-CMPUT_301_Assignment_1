package com.jjwhite.joshua.buzztime;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SinglePlayerRecords extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private DataManager datamanager;
    private TextView two_one;
    private TextView two_two;
    private TextView two_three;
    private TextView three_one;
    private  TextView three_two;
    private TextView three_three;

    private TextView four_one;
    private TextView four_two;
    private TextView four_three;
    private TextView five_one;
    private  TextView five_two;
    private TextView five_three;
    private DecimalFormat df;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_records);
        df = new DecimalFormat("#.###");

        two_one = (TextView) findViewById(R.id.single_2_2);
        two_two = (TextView) findViewById(R.id.single_2_3);
        two_three = (TextView) findViewById(R.id.single_2_4);
        three_one = (TextView) findViewById(R.id.single_3_2);
        three_two = (TextView) findViewById(R.id.single_3_3);
        three_three = (TextView) findViewById(R.id.single_3_4);

        four_one = (TextView) findViewById(R.id.single_4_2);
        four_two = (TextView) findViewById(R.id.single_4_3);
        four_three = (TextView) findViewById(R.id.single_4_4);
        five_one = (TextView) findViewById(R.id.single_5_2);
        five_two = (TextView) findViewById(R.id.single_5_3);
        five_three = (TextView) findViewById(R.id.single_5_4);



        datamanager = DataManager.getInstance(this);
        loadFromFile();
        setStats();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_records, menu);
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

    public void MultiplayerRecords(View view) {
        //Triggered when the user selects "Records" from the menu
        // Will trigger page that displays records
        Intent intent = new Intent(this, MultiplayerRecords.class);
        startActivity(intent);

    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Following line based on gson
            Type listType = new TypeToken<ArrayList<Double>>() {}.getType();
            ArrayList<Double> click_records  = gson.fromJson(in,listType);
            datamanager.getRecords().setClick_tracker(click_records);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            datamanager.setRecordsAL(new ArrayList<Double>());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void saveSingleRecords(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(datamanager.getRecords().getClick_tracker(), writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void clearSinglePlayerRecords(View view){
        datamanager.getRecords().clearSingleRecords();
        saveSingleRecords();
        setStats();
    }

    public void setStats(){
        two_one.setText(df.format(datamanager.getRecords().mean10()));
        two_two.setText(df.format(datamanager.getRecords().mean100()));
        two_three.setText(df.format(datamanager.getRecords().meanAll()));

        three_one.setText(df.format(datamanager.getRecords().meadian10()));
        three_two.setText(df.format((datamanager.getRecords().meadian100())));
        three_three.setText(df.format((datamanager.getRecords().meadianAll())));

        four_one.setText(df.format(datamanager.getRecords().mini(10)));
        four_two.setText(df.format((datamanager.getRecords().mini(100))));
        four_three.setText(df.format((datamanager.getRecords().mini(datamanager.getRecords().getClick_tracker().size()))));

        five_one.setText(df.format(datamanager.getRecords().maxi(10)));
        five_two.setText(df.format((datamanager.getRecords().maxi(100))));
        five_three.setText(df.format((datamanager.getRecords().maxi(datamanager.getRecords().getClick_tracker().size()))));


    }

    public String generateEmail(){

        String email = new String();
        email = "Single Player Records: " + '\n' + '\n';
        email = email + "Last 10 Clicks Average:  " + two_one.getText().toString() + '\n';
        email = email + "Last 100 Clicks Average:  " + two_two.getText().toString() + '\n';
        email = email + "All Clicks Average:  " + two_three.getText().toString() + '\n';

        email = email + "Last 10 Clicks Median:  " + three_one.getText().toString() + '\n';
        email = email + "Last 100 Clicks Median:  " + three_two.getText().toString() + '\n';
        email = email + "All Clicks Median:  " + three_three.getText().toString() + '\n' + '\n';

        email = email + "Last 10 Clicks Min:  " + four_one.getText().toString() + '\n';
        email = email + "Last 100 Clicks Min:  " + four_two.getText().toString() + '\n';
        email = email + "All Clicks Min:  " + four_three.getText().toString() + '\n' + '\n';

        email = email + "Last 10 Clicks Max:  " + five_one.getText().toString() + '\n';
        email = email + "Last 100 Clicks Max:  " + five_two.getText().toString() + '\n';
        email = email + "All Clicks Max:  " + five_three.getText().toString() + '\n' + '\n';

        email = email + "Multiplayer Records: " + '\n' + '\n';

        email = email + "Two player - Player 1:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "1", "2")) + '\n';
        email = email + "Two player - Player 2:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "2", "2")) + '\n';

        email = email + "Three player - Player 1:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "1", "3")) + '\n';
        email = email + "Three player - Player 2:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "2", "3")) + '\n';
        email = email + "Three player - Player 3:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "3", "3")) + '\n';

        email = email + "Four player - Player 1:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "1", "4")) + '\n';
        email = email + "Four player - Player 2:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "2", "4")) + '\n';
        email = email + "Four player - Player 3:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "3", "4")) + '\n';
        email = email + "Four player - Player 4:  " + Integer.toString(datamanager.getRecords().loadMultiStats(getApplicationContext(), "4", "4")) + '\n';

        return email;
    }

    public void sendEmail(View view){

        Intent emailintent = new Intent(Intent.ACTION_SEND);
        emailintent.setData(Uri.parse("mailto:"));
        emailintent.setType("text/plain");
        emailintent.putExtra(Intent.EXTRA_EMAIL, "joshua.jj.white@gmail.com");
        emailintent.putExtra(Intent.EXTRA_SUBJECT, "BuzzTime Stats");
        emailintent.putExtra(Intent.EXTRA_TEXT, generateEmail());
        try{
            startActivity(emailintent);
            finish();
        }
        catch(android.content.ActivityNotFoundException e){
            Toast.makeText(SinglePlayerRecords.this, "There is no email client installed", Toast.LENGTH_SHORT).show();
        }
    }



}


