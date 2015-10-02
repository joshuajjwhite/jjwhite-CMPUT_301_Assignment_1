package com.jjwhite.joshua.buzztime;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Double mean(int index){
        Double temp = 0d;

        if(this.getClick_tracker().size() == 0){
            return temp;
        }
        else if(this.getClick_tracker().size() < index){
            return meanAll();
        }
        else {
            for (int k = 0; k < index; k++) {
                temp += this.click_tracker.get(k);
            }

            return (temp / index);
        }
    }

    public Double mean10(){
        return mean(10);
    }

    public Double mean100(){
        return mean(100);
    }

    public Double meanAll(){
        return mean(this.click_tracker.size());
    }

    public Double meadian(int index){
        if(this.getClick_tracker().size() == 0){
            return 0d;
        }

        else if(this.getClick_tracker().size() < index){
            return meadianAll();
        }
        else {
            List<Double> temp = this.click_tracker.subList(0, index);
            Collections.sort(temp);
            return temp.get((index / 2) - 1);
        }
    }

    public Double meadian10(){
        return meadian(10);
    }

    public Double meadian100(){
        return meadian(100);
    }

    public Double meadianAll(){
        return meadian(this.click_tracker.size());
    }

    public void clearSingleRecords(){
        this.setClick_tracker(new ArrayList<Double>());
    }

    public Double maxi(int index) {
        if (this.getClick_tracker().size() == 0) {
            return 0d;
        } else if (this.getClick_tracker().size() < index) {
            return Collections.max(this.click_tracker);
        } else {
            List<Double> temp = this.click_tracker.subList(0, index);
            return Collections.max(temp);
        }
    }


    public Double mini(int index) {
        if (this.getClick_tracker().size() == 0) {
            return 0d;
        } else if (this.getClick_tracker().size() < index) {
            return Collections.min(this.click_tracker);
        } else {
            List<Double> temp = this.click_tracker.subList(0, index);
            return Collections.min(temp);
        }

    }


}
