package com.google.developer.bugmaster.presenters;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;
import com.google.developer.bugmaster.view.adapters.InsectListViewHolder;

/**
 * Created by Андрей on 17.10.2017.
 */

public interface AppPresenter {

    String INTENT_ALARM_RECIEVER_ACION = "com.google.developer.bugmaster.UPDATE_REMINDER";
    String INTENT_QUIZ_SCREEN_LAUNCH = "quiz launch key";

    int CHOSEN_ANSWER_INDEX_DEFAULT = -1;


    void onAttach();

    void onDetach();

    void onSortMenuItemClick();

    void onSettingsMenuItemClick();

    void onQuizFabClick();

    void onBackButtonClick();

    void onSelectedAnswerListener(int answerIndex);

    int getChoosenAnswerIndex();

    void onInsectListItemClick(int position);

    int getInsectsListCount();

    void onBindInsectListViewHolder(InsectListViewHolder holder, int position);

    Insect getShowingInsect();
    Bitmap qetInsectImage();
    Question qetQuizQuestion();

}
