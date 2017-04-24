package com.willowtree.namegame.controller;

import com.willowtree.namegame.model.Person;

/**
 * Created by Derek on 4/22/2017.
 */

public interface ControllerInt {

    public boolean isCorrect(int image);
    public void updateScore(boolean isCorrect);
    //public void generateView();
}
