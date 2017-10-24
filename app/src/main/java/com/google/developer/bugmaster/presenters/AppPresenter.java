package com.google.developer.bugmaster.presenters;

import com.google.developer.bugmaster.view.adapters.InsectListViewHolder;

/**
 * Created by Андрей on 17.10.2017.
 */

public interface AppPresenter {

    String INTENT_ALARM_RECIEVER_ACION = "com.google.developer.bugmaster.UPDATE_REMINDER";
    String INTENT_QUIZ_SCREEN_LAUNCH = "quiz launch key";

    byte MAIN_SCREEN_ID = 1;
    byte DETAILS_SCREEN_ID = 2;
    byte QUIZ_SCREEN_ID = 3;
    byte SETTINGS_SCREEN_ID = 4;

    int CHOSEN_ANSWER_INDEX_DEFAULT = -1;


    void onAttach();

    void onDetach();

    void onSortMenuItemClick();

    void onSettingsMenuItemClick();

    void onQuizFabClick();

    void onBackButtonClick();

    void onSelectedAnswerListener(int answerIndex);

    int getChoosenAnswerIndex();

    byte getScreenID();

    interface ListPresenterInterface {

    void onInsectListItemClick(int position);

    int getInsectsListCount();

    void onBindInsectListViewHolder(InsectListViewHolder holder, int position);

    }

//    void onBindInsectListRecyclerView(int position, InsectListViewHolder holder);

}
