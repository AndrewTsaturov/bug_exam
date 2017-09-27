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

    String INTENT_QUIZ_SCREEN_LAUNCH_KEY = "screenID_key";
}
