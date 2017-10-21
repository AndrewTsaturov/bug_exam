package com.google.developer.bugmaster.presenters;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.AppBugMaster;
import com.google.developer.bugmaster.model.Insect;
import com.google.developer.bugmaster.model.Model;
import com.google.developer.bugmaster.model.ModelInterface;
import com.google.developer.bugmaster.model.Question;
import com.google.developer.bugmaster.views.AppView;
import com.google.developer.bugmaster.views.InsectListViewHolder;
import com.google.developer.bugmaster.views.ViewInterface;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Андрей on 18.10.2017.
 */

public class Presenter implements PresenterInterface {
    byte screenID;

    boolean isInsectListcompared;

    ArrayList<Insect> insectsList;

    Insect showingInsect;

    int quizViewChoozenAnswerIndex;

    Question quizQuestion;

    Bitmap insectImage;

    ModelInterface model = new Model();
    ViewInterface view;

    public Presenter() {
    }

    @Override
    public void onAttach() {
        if(insectsList.size() != 0)
        setInsectsList(model.loadData());

        view = new AppView();
        view.showInsectList();

            switch (screenID){
                case MAIN_SCREEN_ID:
                    view.showInsectList();
                    break;
                case PresenterInterface.DETAILS_SCREEN_ID:
                    view.showInsectDetails(showingInsect, insectImage);
                    break;
                case QUIZ_SCREEN_ID:
                    view.showQuiz(quizQuestion);
                    break;
                case SETTINGS_SCREEN_ID:
                    view.showSettings();
                    break;
                default:
                    view.showInsectList();
                    break;
            }

    }

    @Override
    public void onDetach() {
        view = null;
    }

    @Override
    public void onSortMenuItemClick() {
        if(isInsectListcompared) {
            Collections.sort(insectsList, new Insect.DangerLevelComparator());

            setInsectListcompared(false);
        }
        else {
            Collections.sort(insectsList, new Insect.CommonNameComparator());

            setInsectListcompared(true);
        }
    }

    @Override
    public void onSettingsMenuItemClick() {
        setScreenID(SETTINGS_SCREEN_ID);

        view.showSettings();
    }

    @Override
    public void onQuizFabClick() {
        setQuizViewChoozenAnswerIndex(CHOSEN_ANSWER_INDEX_DEFAULT);

        quizQuestion = model.createQuestion();

        setScreenID(QUIZ_SCREEN_ID);

        view.showQuiz(quizQuestion);
    }

    @Override
    public void onBackButtonClick() {
        if(screenID != MAIN_SCREEN_ID){
            view.showInsectList();

            setScreenID(MAIN_SCREEN_ID);
        }
        else {
            view.stopView();
        }
    }

    @Override
    public void onSelectedAnswerListener( int answerIndex) {
        setQuizViewChoozenAnswerIndex(answerIndex);
    }

    @Override
    public int getChoosenAnswerIndex() {
        return getQuizViewChoozenAnswerIndex();
    }

    @Override
    public byte getScreenID() {
        return screenID;
    }

    @Override
    public void onInsectListItemClick(int position) {
        showingInsect = insectsList.get(position);

        insectImage = model.loadInsectImage(showingInsect.getImageAsset());

        setScreenID(DETAILS_SCREEN_ID);

        view.showInsectDetails(showingInsect, insectImage);
    }

    @Override
    public int getInsectsListCount() {
        return insectsList.size();
    }

    @Override
    public void onBindInsectListViewHolder(InsectListViewHolder holder, int position) {
        holder.setDangerLevel(insectsList.get(position).getDangerLevel());
        holder.setCommonName(insectsList.get(position).getName());
        holder.setScientificName(insectsList.get(position).getScientificName());
    }

    public void setInsectsList(ArrayList<Insect> insectsList) {
        this.insectsList = insectsList;
    }

    public void setInsectListcompared(boolean insectListcompared) {
        isInsectListcompared = insectListcompared;
    }

    public void setScreenID(byte screenID) {
        this.screenID = screenID;
    }

    public int getQuizViewChoozenAnswerIndex() {
        return quizViewChoozenAnswerIndex;
    }

    public void setQuizViewChoozenAnswerIndex(int quizViewChoozenAnswerIndex) {
        this.quizViewChoozenAnswerIndex = quizViewChoozenAnswerIndex;
    }
}
