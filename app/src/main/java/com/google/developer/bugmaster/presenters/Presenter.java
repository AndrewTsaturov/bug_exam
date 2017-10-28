package com.google.developer.bugmaster.presenters;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.developer.bugmaster.model.AppModel;
import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;
import com.google.developer.bugmaster.view.AppView;
import com.google.developer.bugmaster.view.adapters.InsectListViewHolder;

import static com.google.developer.bugmaster.view.AppView.DETAILS_SCREEN_ID;
import static com.google.developer.bugmaster.view.AppView.MAIN_SCREEN_ID;
import static com.google.developer.bugmaster.view.AppView.QUIZ_SCREEN_ID;
import static com.google.developer.bugmaster.view.AppView.SETTINGS_SCREEN_ID;

/**
 * Created by Андрей on 18.10.2017.
 */
public class Presenter implements AppPresenter {


    private boolean isInsectListcompared;

    AppModel model;
    AppView view;

    public Presenter(AppModel model, AppView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onAttach() {
        Log.d("Pres", "" + view.getScreenId());
        switch (view.getScreenId()) {
            case MAIN_SCREEN_ID:
                view.showInsectList();
                break;

            case DETAILS_SCREEN_ID:
                view.showInsectDetails();
                break;

            case QUIZ_SCREEN_ID:
                view.showQuiz();
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
        if (isInsectListcompared) {
            model.sortList(isInsectListcompared);
            isInsectListcompared = false;
        } else {
            model.sortList(isInsectListcompared);
            isInsectListcompared = true;
        }

        view.showInsectList();
    }

    @Override
    public void onSettingsMenuItemClick() {
        view.showSettings();
    }

    @Override
    public void onQuizFabClick() {
        model.createQuestion();

        view.showQuiz();
    }

    @Override
    public void onBackButtonClick() {
        switch (view.getScreenId()){
            case MAIN_SCREEN_ID:
                view.stopView();
                break;
            case SETTINGS_SCREEN_ID:
                model.sendSettingsChangedBroadCast();
                view.showInsectList();
                break;
            default:
                view.showInsectList();
                break;
        }
    }

    @Override
    public void onSelectedAnswerListener(int answerIndex) {
        model.setSelectedQuizAnswer(answerIndex);
    }

    @Override
    public int getChoosenAnswerIndex() {
        return model.getSelectedQuizAnswer();
    }

    @Override
    public void onInsectListItemClick(int position) {
        model.setShowingInsect(position);
        model.loadInsectImage(model.getShowingInsect().getImageAsset());

        view.showInsectDetails();
    }

    @Override
    public int getInsectsListCount() {
        return model.getInsectList().size();
    }

    @Override
    public void onBindInsectListViewHolder(InsectListViewHolder holder, int position) {
        holder.setDangerLevel(model.getInsectList().get(position).getDangerLevel());
        holder.setCommonName(model.getInsectList().get(position).getName());
        holder.setScientificName(model.getInsectList().get(position).getScientificName());
    }

    @Override
    public Insect getShowingInsect() {
        return model.getShowingInsect();
    }

    @Override
    public Bitmap qetInsectImage() {
        return model.getInsectImage();
    }

    @Override
    public Question qetQuizQuestion() {
        return model.getQuestion();
    }

}

