package com.willowtree.namegame.controller;

import com.willowtree.namegame.view.ReverseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derek on 4/23/2017.
 */

public class ReverseController implements ControllerInt {
    private ReverseFragment fragment;
    private MasterFragmentController masterController;

    public ReverseController(ReverseFragment fragment) {
        this.fragment = fragment;
        this.masterController = new MasterFragmentController(fragment.getActivity());
    }


    @Override
    public boolean isCorrect(int id) {
        if (id == masterController.getRandomPeople().indexOf(masterController.getCorrectPerson())) {
            masterController.updateScore(true);
            return true;
        }
        masterController.updateScore(false);
        return false;
    }

    @Override
    public void updateScore(boolean isCorrect) {

    }


    public void generateView() {
        masterController.updateRandomizer();
        List<String> names = new ArrayList<String>(5);
        for (int i = 0; i < masterController.getRandomPeople().size(); i++) {
            names.add(masterController.getRandomPeople().get(i).getFirstName() +
                    " " +
                    masterController.getRandomPeople().get(i).getLastName());
        }
        fragment.setRadioButtons(names, masterController
                .getRandomPeople()
                .indexOf(masterController.getCorrectPerson()));
        fragment.setImage(masterController.getCorrectPerson().getHeadshot().getUrl());
        fragment.initAnswerGroupListener();
    }



}
