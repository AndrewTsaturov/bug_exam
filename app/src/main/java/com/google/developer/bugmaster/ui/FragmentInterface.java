package com.google.developer.bugmaster.ui;

/**
 * Created by Андрей on 19.09.2017.
 */

public interface FragmentInterface {
    void sortInsectList();

    void detailsScreenLaunch(int position);

    void settingsScreenLaunch();

    void quizScreenLaunch();

    void listScreenLaunch();

    byte MAIN_SCREEN_ID = 1;
    byte DETAILS_SCREEN_ID = 2;
    byte QUIZ_SCREEN_ID = 3;
    byte SETTINGS_SCREEN_ID = 4;

    String INTENT_QUIZ_SCREEN_LAUNCH = "quiz launch key";
}
