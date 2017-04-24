package com.willowtree.namegame;

import android.app.Application;

/**
 * Created by Derek on 4/23/2017.
 */

public class NameGameApplication extends Application {

    private int correct;
    private int questions;
    private long seconds;

    public String getCorrect() {
        return correct + " / " + questions;
    }

    public long getSeconds() {
        return questions == 0 ? 0 : (seconds/questions)/1000;
    }

    public void reset() {
        correct = 0;
        questions = 0;
    }

    public void increment(boolean isCorrect) {
        if (isCorrect) {
            correct++;
            questions++;
        } else {
            questions++;
        }
    }

    public void incrementSeconds(long seconds) {
        this.seconds += seconds;
    }

}
