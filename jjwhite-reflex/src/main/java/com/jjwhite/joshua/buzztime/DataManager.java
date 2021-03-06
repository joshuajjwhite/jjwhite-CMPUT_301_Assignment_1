package com.jjwhite.joshua.buzztime;


import android.content.Context;
import android.os.SystemClock;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Joshua on 2015-09-28.
 */
public class DataManager {

    RecordDealer records;
    private GamePlay game;
    private Context context;
    private static DataManager instance;

    private DataManager(Context context) {
        records = new RecordDealer();
        this.context = context;
        game = new GamePlay();
    }

    //used to create the Datamanager object
    public static DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context.getApplicationContext());
        }
        return instance;
    }


    public void changeState(String state){
        game.setGamestate(state);
    }


    public long getRandomDelay(){
        return   game.getRandomdelay().longValue();
    }

    public void gamePrep(){
        if(game.getGamestate().equals("START")){
            game.nextRand();
            game.setLasttime();
        }

    }

    public String readyTest(){
        if(game.getGamestate().equals("START")){
            game.setGamestate("CLICK!");
            game.setLasttime();
            return "CLICK!";
        }

        //Should never show up
        else{return "STARTOVER";}
    }

    public String handleClick(){
        if( game.tooEarly() ){
            return "TOO EARLY!";
        }

        else {
            Double temp = game.calcLatency();
            this.addRecord(temp);
            return Double.toString(temp);}

    }

    public RecordDealer getRecords() {
        return records;
    }

    //sets the latency record ArrayList to the give ArrayList
    public void setRecordsAL(ArrayList<Double> al){
        this.getRecords().setClick_tracker(al);
    }

    public void addRecord(Double record){
        this.getRecords().getClick_tracker().add(0,record);
    }
}