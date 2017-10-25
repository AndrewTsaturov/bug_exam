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
//TODO: старый презентер не годен, я перереализую его после того как решим вопрос с репозиторием
    //TODO: переписать используя джененрики?
public class Presenter implements AppPresenter {
    String TAG = "Presenter";

    private static byte screenID;

    private boolean isInsectListcompared;

    static ModelInterface model;
    static AppView view;


    public Presenter(AppView view)
    {
        //Fixme агрегация или композиция
        this.view = view;
        model = new Model();
    }


    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSortMenuItemClick() {

    }

    @Override
    public void onSettingsMenuItemClick() {

    }

    @Override
    public void onQuizFabClick() {

    }

    @Override
    public void onBackButtonClick() {

    }

    @Override
    public void onSelectedAnswerListener(int answerIndex) {

    }

    @Override
    public int getChoosenAnswerIndex() {
        return 0;
    }

    @Override
    public byte getScreenID() {
        return 0;
    }
}

