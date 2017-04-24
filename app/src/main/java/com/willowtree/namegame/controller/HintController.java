package com.willowtree.namegame.controller;

import android.os.Handler;

import com.willowtree.namegame.model.Person;
import com.willowtree.namegame.view.NormalFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Derek on 4/24/2017.
 */

public class HintController implements ControllerInt {
    private NormalFragment view;
    private MasterFragmentController masterController;
    private Timer imageTimer;
    private TimerTask timerTask;
    final Handler handler = new Handler();
    private List<Person> visibleImages;

    public HintController(NormalFragment view) {
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
        masterController.updateRandomizer();
        view.setImages(masterController.getRandomPeople());
        view.genComponents(masterController.getCorrectPerson().getFirstName() +
                " " +
                masterController.getCorrectPerson().getLastName());
        generateLogic();
    }

    private void generateLogic() {
        populateVisibleImages();
        startTimer();
    }

    private void populateVisibleImages() {
        visibleImages = new ArrayList<Person>(4);
        for (int i = 0; i < masterController.getRandomPeople().size(); i++) {
            Person person = masterController.getRandomPeople().get(i);
            if (!person.equals(masterController.getCorrectPerson())) {
                visibleImages.add(person);
            }
        }
    }

    private void startTimer() {
        imageTimer = new Timer();
        initTimerTask();
        imageTimer.schedule(timerTask, 7000, 7000);
    }

    private void stopTimerTask() {
        if (imageTimer != null) {
            imageTimer.cancel();
            imageTimer = null;
        }
    }

    public void initTimerTask() {

        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (visibleImages.size() == 0) {
                            stopTimerTask();
                        } else {
                            Person person = masterController.getRandomizer().pickOne(visibleImages);
                            view.hidePerson(person.getHeadshot().getAlt());
                            visibleImages.remove(person);
                        }
                    }
                });
            }
        };
    }
}
