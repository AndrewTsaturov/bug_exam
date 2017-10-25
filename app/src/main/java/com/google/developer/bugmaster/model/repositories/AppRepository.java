package com.google.developer.bugmaster.model.repositories;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;

import java.util.ArrayList;

/**
 * Created by Дом on 24.10.2017.
 */
//TODO: класс для хранения данных для работы приложения: интересует можно ли его сделать сингтоном?
    //// TODO: или таки его надо использовать через введение зависоимоти или даггер?
public class AppRepository {
    ArrayList<Insect> insectsList;

    Insect showingInsect;

    Bitmap showingInsectImage;

    Question quizQestion;

    int selectedQuizAnswer;

    public AppRepository() {
    }

    public ArrayList<Insect> getInsectsList() {
        return insectsList;
    }

    public void setInsectsList(ArrayList<Insect> insectsList) {
        this.insectsList = insectsList;
    }

    public Insect getShowingInsect() {
        return showingInsect;
    }

    public void setShowingInsect(Insect showingInsect) {
        this.showingInsect = showingInsect;
    }

    public Bitmap getShowingInsectImage() {
        return showingInsectImage;
    }

    public void setShowingInsectImage(Bitmap showingInsectImage) {
        this.showingInsectImage = showingInsectImage;
    }

    public Question getQuizQestion() {
        return quizQestion;
    }

    public void setQuizQestion(Question quizQestion) {
        this.quizQestion = quizQestion;
    }

    public int getSelectedQuizAnswer() {
        return selectedQuizAnswer;
    }

    public void setSelectedQuizAnswer(int selectedQuizAnswer) {
        this.selectedQuizAnswer = selectedQuizAnswer;
    }
}
