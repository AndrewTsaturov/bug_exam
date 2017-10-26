package com.google.developer.bugmaster.presenters;

import com.google.developer.bugmaster.model.AppModel;
import com.google.developer.bugmaster.view.AppView;
import com.google.developer.bugmaster.view.adapters.InsectListViewHolder;

/**
 * Created by Андрей on 18.10.2017.
 */
//TODO: старый презентер не годен, я перереализую его после того как решим вопрос с репозиторием
    //TODO: переписать используя джененрики?
public class Presenter implements AppPresenter {

    private static byte screenID;

    private boolean isInsectListcompared;

    AppModel model;
    AppView view;

    public Presenter(AppModel model, AppView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onAttach() {
        switch (screenID){
            case MAIN_SCREEN_ID:
                view.showInsectList();
                break;

            case DETAILS_SCREEN_ID:
                view.showInsectDetails(model.getShowingInsect(), model.getInsectImage());
                break;

            case QUIZ_SCREEN_ID:
                view.showQuiz(model.getQuestion());
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
        //FIXME не нужен пока что
    }

    @Override
    public void onSortMenuItemClick() {
        if(isInsectListcompared){
       model.sortList(isInsectListcompared);
        isInsectListcompared = false;
        }
        else {
            model.sortList(isInsectListcompared);
            isInsectListcompared = true;
        }

    }

    @Override
    public void onSettingsMenuItemClick() {
        view.showSettings();
    }

    @Override
    public void onQuizFabClick() {
        model.createQuestion();

        view.showQuiz(model.getQuestion());
    }

    @Override
    public void onBackButtonClick() {
        if(screenID == MAIN_SCREEN_ID)
            view.stopView();
        else
            view.showInsectList();
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

        view.showInsectDetails(model.getShowingInsect(), model.getInsectImage());
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
}
