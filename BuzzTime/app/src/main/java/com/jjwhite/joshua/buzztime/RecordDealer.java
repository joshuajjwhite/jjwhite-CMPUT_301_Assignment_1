package com.jjwhite.joshua.buzztime;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Created by Joshua on 2015-09-15.
 */
public class RecordDealer {


    private ArrayList<Double> click_tracker; // For single player stats

    public RecordDealer(ArrayList<Double> arraylist){

        this.click_tracker = arraylist;
    }

    public RecordDealer(){
        this.click_tracker = new ArrayList<Double>();
    }


    public void saveMultiStats(Context context, String key, int i, String pref) {

        SharedPreferences game_stats = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_stats = game_stats.edit();
        edit_stats.putInt(key, i);
        edit_stats.apply();
    }

    public int loadMultiStats(Context context, String key, String pref){

        SharedPreferences game_stats = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        return (game_stats.getInt(key, 0));

    }

    public void incrimentStats(Context context, String player_index, int amount_of_players){

        int temp = loadMultiStats(context, player_index, Integer.toString(amount_of_players));
        temp += 1;
        saveMultiStats(context,player_index,temp,Integer.toString(amount_of_players));

    }

    public ArrayList<Double> getClick_tracker() {
        return this.click_tracker;
    }

    public void setClick_tracker(ArrayList<Double> click_tracker) {
        this.click_tracker = click_tracker;
    }

    public void clearMultiplayerRecords(Context context){
        for(int i = 2; i <= 4; i++){
            SharedPreferences game_stats = context.getSharedPreferences(Integer.toString(i), Context.MODE_PRIVATE);
            SharedPreferences.Editor edit_stats = game_stats.edit();
            edit_stats.clear();
            edit_stats.apply();


        }
    }



}
