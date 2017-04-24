package com.willowtree.namegame.controller;

import com.willowtree.namegame.view.NormalFragment;

/**
 * Created by Derek on 4/24/2017.
 */

public class MatController implements ControllerInt{
    private NormalFragment view;
    private MasterFragmentController masterController;

    public MatController(NormalFragment view) {
        this.view = view;
        this.masterController = new MasterFragmentController(view.getActivity());
    }

    @Override
    public boolean isCorrect(int image) {
        boolean isCorrect = masterController.isCorrect(image);
        if (isCorrect) {
            view.setCorrectFilter(image);
            return true;
        }
        view.setInCorrectFilter(image);
        return false;
    }

    @Override
    public void updateScore(boolean isCorrect) {

    }

    public void generateView() {
        view.loadFaces();
        view.loadNames();
        masterController.updateMatRandomizer();
        view.setImages(masterController.getRandomPeople());
        view.genComponents(masterController.getCorrectPerson().getFirstName() +
                " " +
                masterController.getCorrectPerson().getLastName());
    }
}
