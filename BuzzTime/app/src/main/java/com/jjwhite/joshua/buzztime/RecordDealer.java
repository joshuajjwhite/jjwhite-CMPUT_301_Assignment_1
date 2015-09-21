package com.jjwhite.joshua.buzztime;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Joshua on 2015-09-15.
 */
public class RecordDealer {

    private ArrayList<Long> click_tracker; // For single player stats

    public RecordDealer(ArrayList<Long> arraylist){

        this.click_tracker = arraylist;
    }

    public RecordDealer(){
        this.click_tracker = new ArrayList<Long>();
    }


    public void saveStats(Context context, String key, int i, String pref) {

        SharedPreferences game_stats = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_stats = game_stats.edit();
        edit_stats.putInt(key, i);
        edit_stats.apply();
    }

    public int loadStats(Context context, String key, String pref){

        SharedPreferences game_stats = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        return (game_stats.getInt(key, 0));

    }

    public void incrimentStats(Context context, String player_index, int amount_of_players){

        int temp = loadStats(context, player_index, Integer.toString(amount_of_players));
        temp += 1;
        saveStats(context,player_index,temp,Integer.toString(amount_of_players));

    }

    public ArrayList<Long> getClick_tracker() {
        return this.click_tracker;
    }

    public void setClick_tracker(ArrayList<Long> click_tracker) {
        this.click_tracker = click_tracker;
    }

    public void clearMultiplayerRecords(Context context){
        for(int i = 2; i <= 4; i++){
            SharedPreferences game_stats = context.getSharedPreferences(Integer.toString(i), Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = game_stats.edit();
            edit.clear();
        }
    }
}
