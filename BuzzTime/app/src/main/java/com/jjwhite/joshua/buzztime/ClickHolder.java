package com.jjwhite.joshua.buzztime;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class ClickHolder {

    private ArrayList<Double> single_clicks;
    private HashMap<String, Double> two_player_clicks;
    private HashMap<String, Double> three_player_clicks;
    private HashMap<String, Double> four_player_clicks;

    public ClickHolder(){

        single_clicks = new ArrayList<Double>();
        two_player_clicks = new HashMap<String, Double>();
        three_player_clicks = new HashMap<String, Double>();
        four_player_clicks = new HashMap<String, Double>();

    }


    public ArrayList<Double> getSingle_clicks() {
        return single_clicks;
    }

    public void setSingle_clicks(ArrayList<Double> single_clicks) {
        this.single_clicks = single_clicks;
    }

    public HashMap<String, Double> getTwo_player_clicks() {
        return two_player_clicks;
    }

    public void setTwo_player_clicks(HashMap<String, Double> two_player_clicks) {
        this.two_player_clicks = two_player_clicks;
    }

    public HashMap<String, Double> getThree_player_clicks() {
        return three_player_clicks;
    }

    public void setThree_player_clicks(HashMap<String, Double> three_player_clicks) {
        this.three_player_clicks = three_player_clicks;
    }

    public HashMap<String, Double> getFour_player_clicks() {
        return four_player_clicks;
    }

    public void setFour_player_clicks(HashMap<String, Double> four_player_clicks) {
        this.four_player_clicks = four_player_clicks;
    }





}
