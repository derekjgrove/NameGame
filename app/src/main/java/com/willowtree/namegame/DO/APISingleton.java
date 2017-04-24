package com.willowtree.namegame.DO;

import com.willowtree.namegame.model.Person;

import java.util.ArrayList;

/**
 * Created by Derek on 4/20/2017.
 */

public class APISingleton {
    private static APISingleton mInstance = null;
    private ArrayList<Person> people;

    private APISingleton() {
        people = new ArrayList<Person>();
    }

    public static APISingleton getInstance() {
        if (mInstance == null) {
            mInstance = new APISingleton();
        }
        return mInstance;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }
}
