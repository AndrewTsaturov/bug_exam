package com.google.developer.bugmaster.model;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;

import java.util.ArrayList;

/**
 * Created by Андрей on 17.10.2017.
 */

public interface AppModel {
   String ALARM_RECIEVER_ACION = "com.google.developer.bugmaster.UPDATE_REMINDER";

    int CHOSEN_ANSWER_INDEX_DEFAULT = -1;

    void loadData();

    void createQuestion();

    void loadInsectImage(String imageAsset);

    void setShowingInsect(int position);

    void setSelectedQuizAnswer(int selectedQuizAnswer);

    void sortList(boolean comparingFlag);

    void sendSettingsChangedBroadCast();

    ArrayList<Insect> getInsectList();

    Question getQuestion();

    Insect getShowingInsect();

    Bitmap getInsectImage();

    int getSelectedQuizAnswer();
}
