package com.google.developer.bugmaster.view;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;

/**
 * Created by Андрей on 17.10.2017.
 */

public interface AppView {

    void showInsectList();
    void showInsectDetails(Insect insect, Bitmap insectImage);
    void showQuiz(Question quizQuestion);
    void showSettings();

    void stopView();

}
