package com.google.developer.bugmaster.view;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;

/**
 * Created by Андрей on 17.10.2017.
 */

public interface AppView {

    byte MAIN_SCREEN_ID = 1;
    byte DETAILS_SCREEN_ID = 2;
    byte QUIZ_SCREEN_ID = 3;
    byte SETTINGS_SCREEN_ID = 4;

    void showInsectList();
    void showInsectDetails();
    void showQuiz();
    void showSettings();


    void stopView();

    void setScreenId(byte screenId);

    byte getScreenId();
}
