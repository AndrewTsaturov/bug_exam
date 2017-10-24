package com.google.developer.bugmaster.presenters;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.Model;
import com.google.developer.bugmaster.model.ModelInterface;
import com.google.developer.bugmaster.model.pojo.Question;
import com.google.developer.bugmaster.view.adapters.InsectListViewHolder;
import com.google.developer.bugmaster.view.AppView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Андрей on 18.10.2017.
 */

public class Presenter implements AppPresenter {
    String TAG = "Presenter";

    private static byte screenID;

    private boolean isInsectListcompared;

    private static int quizViewChoozenAnswerIndex;

    public Question quizQuestion;

    public static Bitmap insectImage;

    static ModelInterface model;
    static AppView view;


    public Presenter(AppView view)
    {
        this.view = view;
        model = new Model();
    }

    @Override
    public void onAttach() {
        if(insectsList.size() == 0)
        setInsectsList(model.loadData());

            switch (getScreenID()){
                case MAIN_SCREEN_ID:
                    view.showInsectList();
                    break;
                case AppPresenter.DETAILS_SCREEN_ID:
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
        this.view = null;
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

        view.showInsectList();
    }

    @Override
    public void onSettingsMenuItemClick() {
        screenID = DETAILS_SCREEN_ID;

        view.showSettings();
    }

    @Override
    public void onQuizFabClick() {
        setQuizViewChoozenAnswerIndex(CHOSEN_ANSWER_INDEX_DEFAULT);

        quizQuestion = model.createQuestion();

        screenID = DETAILS_SCREEN_ID;

        view.showQuiz(quizQuestion);
    }

    @Override
    public void onBackButtonClick() {
        if(screenID != MAIN_SCREEN_ID){
            view.showInsectList();

            screenID = MAIN_SCREEN_ID;
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

    public void setInsectsList(ArrayList<Insect> insectsList) {
        this.insectsList = insectsList;
    }

    public void setInsectListcompared(boolean insectListcompared) {
        isInsectListcompared = insectListcompared;
    }

    public int getQuizViewChoozenAnswerIndex() {
        return quizViewChoozenAnswerIndex;
    }

    public void setQuizViewChoozenAnswerIndex(int quizViewChoozenAnswerIndex) {
        this.quizViewChoozenAnswerIndex = quizViewChoozenAnswerIndex;
    }

    public static Insect getShowingInsect() {
        return showingInsect;
    }

    public static void setShowingInsect(Insect showingInsect) {
        Presenter.showingInsect = showingInsect;
    }

    public static class ListPresenter implements AppPresenter.ListPresenterInterface{

        public ListPresenter() {
        }

            @Override
    public void onInsectListItemClick(int position) {
        checkedPosition = position;

        showingInsect = insectsList.get(position);

        insectImage = model.loadInsectImage(showingInsect.getImageAsset());

        screenID = DETAILS_SCREEN_ID;

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

    }
}
