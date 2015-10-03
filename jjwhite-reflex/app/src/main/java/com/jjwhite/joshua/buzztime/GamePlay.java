package com.jjwhite.joshua.buzztime;

import android.os.SystemClock;

import java.util.Random;

/**
 * Created by Joshua on 2015-09-28.
 */
public class GamePlay {

    private String gamestate;
    private Double lasttime;
    private Double randomdelay;
    private Random rand;

    public GamePlay(){

        this.gamestate = "WAIT";
        rand = new Random();
        nextRand();
        setLasttime();
    }

    public String getGamestate() {
        return gamestate;
    }

    public void setGamestate(String gamestate) {
        this.gamestate = gamestate;
    }

    public Double getLasttime() {
        return this.lasttime;
    }

    public void setLasttime() {
        this.lasttime= new Double((SystemClock.elapsedRealtime()));
    }

    public Double getRandomdelay() {return this.randomdelay;
    }

    public void setRandomdelay(double randomdelay) {
        this.randomdelay = randomdelay;
    }

    public Double calcLatency(){
        return (SystemClock.elapsedRealtime() - this.getLasttime())/1000;
    }

    public void nextRand(){
        setRandomdelay((this.rand.nextDouble() * 1950.0d) + 10.0d);
    }

    public boolean tooEarly(){
        if((this.getGamestate().equals("CLICK!"))){ return false;}


        else {return true;}

    }
}
