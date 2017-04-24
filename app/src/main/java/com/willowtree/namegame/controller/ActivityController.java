package com.willowtree.namegame.controller;

import com.willowtree.namegame.DAO.APITask;
import com.willowtree.namegame.view.MainActivity;

/**
 * Created by Derek on 4/23/2017.
 */

public class ActivityController {
    MainActivity view;

    public ActivityController(MainActivity view) {
        this.view = view;
        new APITask(this).execute(new String[] {"https://willowtreeapps.com/api//v1.0/profiles"});
    }

    public void hideLoader() {
        view.hideLoader();
    }

}
