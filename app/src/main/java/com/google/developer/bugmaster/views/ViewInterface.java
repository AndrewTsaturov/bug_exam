package com.google.developer.bugmaster.views;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.model.Insect;
import com.google.developer.bugmaster.model.Question;

/**
 * Created by Андрей on 17.10.2017.
 */

public interface ViewInterface {


    void showInsectList();
    void showInsectDetails(Insect insect, Bitmap insectImage);
    void showQuiz(Question quizQuestion);
    void showSettings();

    void stopView();

}
