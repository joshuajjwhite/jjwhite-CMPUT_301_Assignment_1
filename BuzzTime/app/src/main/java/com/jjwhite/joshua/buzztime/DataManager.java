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
    private DataManager datamanager;
    private Context context;
    private static DataManager instance;

    private DataManager(Context context) {
        records = new RecordDealer();
        this.context = context;
        game = new GamePlay();
    }


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

        else{return "STARTOVER";}
    }

    public String handleClick(){
        if( game.tooEarly() ){
            return "TOOEARLY!";
        }

        else {
            Double temp = game.calcLatency();
            this.addRecord(temp);
            return Double.toString(temp);}

    }

    public RecordDealer getRecords() {
        return records;
    }

    public void setRecords(RecordDealer records) {
        this.records = records;
    }

    public void setRecordsAL(ArrayList<Double> al){
        this.getRecords().setClick_tracker(al);
    }

    public void addRecord(Double record){
        this.getRecords().getClick_tracker().add(0,record);
    }
}