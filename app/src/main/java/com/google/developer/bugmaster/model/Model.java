package com.google.developer.bugmaster.model;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.model.data.InsectImageLoader;
import com.google.developer.bugmaster.model.data.InsectsDbManager;
import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;
import com.google.developer.bugmaster.model.repositories.AppRepository;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Андрей on 17.10.2017.
 */
//TODO: я полностю переопределю модель после того как ты скажешь мне как правильно хранить данные в репозиториях

public class Model implements AppModel {

    private AppRepository appRepository;

    private InsectsDbManager DbManager;
    private InsectImageLoader imageLoader;

    public Model(AppRepository appRepository) {
        this.appRepository = appRepository;

        DbManager = new InsectsDbManager(appRepository.getContext());

        imageLoader = new InsectImageLoader(appRepository.getContext());
    }

    @Override
    public void loadData() {
        appRepository.setInsectsList(DbManager.loadInsects());
    }

    @Override
    public void createQuestion() {
        Question quizQuestion = new Question();
        quizQuestion.prepareQuestion(appRepository.getInsectsList());

        appRepository.setQuizQestion(quizQuestion);
    }

    @Override
    public void loadInsectImage(String imageAsset) {
        appRepository.setShowingInsectImage(imageLoader.loadImage(imageAsset));
    }

    @Override
    public void setShowingInsect(int position) {
        appRepository.setShowingInsect(appRepository.getInsectsList().get(position));
    }

    @Override
    public void setSelectedQuizAnswer(int selectedQuizAnswer) {
        appRepository.setSelectedQuizAnswer(selectedQuizAnswer);
    }

    @Override
    public void sortList(boolean comparingFlag) {
        if(comparingFlag)
            Collections.sort(appRepository.getInsectsList(), new Insect.DangerLevelComparator());
        else
            Collections.sort(appRepository.getInsectsList(), new Insect.CommonNameComparator());
    }

    @Override
    public ArrayList<Insect> getInsectList() {
        return appRepository.getInsectsList();
    }

    @Override
    public Question getQuestion() {
        return appRepository.getQuizQestion();
    }

    @Override
    public Insect getShowingInsect() {
        return appRepository.getShowingInsect();
    }

    @Override
    public Bitmap getInsectImage() {
        return appRepository.getShowingInsectImage();
    }

    @Override
    public int getSelectedQuizAnswer() {
        return appRepository.getSelectedQuizAnswer();
    }

//Fixme: переопределить после решения вопроса по хранению данных


}
