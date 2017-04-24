package com.willowtree.namegame.controller;

import android.app.Activity;

import com.willowtree.namegame.DO.APISingleton;
import com.willowtree.namegame.NameGameApplication;
import com.willowtree.namegame.model.Person;
import com.willowtree.namegame.util.ListRandomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Derek on 4/24/2017.
 */

public class MasterFragmentController implements ControllerInt {
    private List<Person> people = APISingleton.getInstance().getPeople();
    private ListRandomizer randomizer = new ListRandomizer(new Random(System.currentTimeMillis()));
    private List<Person> randomPeople;
    private Person correctPerson;
    private boolean hasAnswered = false;
    private Activity activity;
    private long startTime;


    public MasterFragmentController(Activity activity) {
        this.activity = activity;
        this.startTime = System.currentTimeMillis();
    }

    public void updateRandomizer() {
        randomizer = new ListRandomizer(new Random(System.currentTimeMillis()));
        randomPeople = randomizer.pickN(people,5);
        correctPerson = randomizer.pickOne(randomPeople);
    }

    public void updateMatRandomizer() {
        randomizer = new ListRandomizer(new Random(System.currentTimeMillis()));
        randomPeople = new ArrayList<Person>(5);
        while (randomPeople.size()!=5) {
            Person person = randomizer.pickOne(people);
            if (person.getFirstName().subSequence(0,3).equals("Mat")) {
                if(!randomPeople.contains(person)) {
                    randomPeople.add(person);
                }
            }
        }
        correctPerson = randomizer.pickOne(randomPeople);
    }

    @Override
    public boolean isCorrect(int image) {
            if ((randomPeople.get(image).getFirstName() + " " + randomPeople.get(image).getLastName())
                    .equals(correctPerson.getFirstName() + " " + correctPerson.getLastName())) {
                updateScore(true);
                return true;
            } else {
                updateScore(false);
            }
        return false;
    }

    @Override
    public void updateScore(boolean isCorrect) {
        if (!hasAnswered) {
            ((NameGameApplication) activity.getApplication()).incrementSeconds(System.currentTimeMillis() - startTime);
            hasAnswered = true;
            ((NameGameApplication) activity.getApplication()).increment(isCorrect);
        }
    }

    public List<Person> getRandomPeople() {
        return randomPeople;
    }


    public Person getCorrectPerson() {
        return correctPerson;
    }

    public ListRandomizer getRandomizer() {
        return randomizer;
    }
}
