package com.jjwhite.joshua.buzztime;

import android.os.SystemClock;

import java.util.Random;

/**
 * Created by Joshua on 2015-09-28.
 */
public class GamePlay {

    private String gamestate;
    private double lasttime;
    private double randomdelay;
    private Random rand;

    public GamePlay(){

        this.gamestate = "Wait";
        this.randomdelay = (rand.nextDouble() * 2510.0d) - 10.0d;
        this.lasttime = 0d;
    }

    public String getGamestate() {
        return gamestate;
    }

    public void setGamestate(String gamestate) {
        this.gamestate = gamestate;
    }

    public double getLasttime() {
        return lasttime;
    }

    public void setLasttime(double lasttime) {
        this.lasttime = lasttime;
    }

    public double getRandomdelay() {
        return randomdelay;
    }

    public void setRandomdelay(double randomdelay) {
        this.randomdelay = randomdelay;
    }

    public double calcLatency(double lasttime, double randomdelay){
        return SystemClock.elapsedRealtime() - this.getLasttime() - this.getLasttime();
    }
}
