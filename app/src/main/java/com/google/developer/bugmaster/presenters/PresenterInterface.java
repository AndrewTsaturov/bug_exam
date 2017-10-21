package com.google.developer.bugmaster.presenters;

import com.google.developer.bugmaster.views.InsectListViewHolder;

/**
 * Created by Андрей on 17.10.2017.
 */

public interface PresenterInterface {

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

    void onInsectListItemClick(int position);

    int getInsectsListCount();

    void onBindInsectListViewHolder(InsectListViewHolder holder, int position);

//    void onBindInsectListRecyclerView(int position, InsectListViewHolder holder);

}
