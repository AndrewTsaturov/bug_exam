package com.google.developer.bugmaster.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.developer.bugmaster.AppBugMaster;

import java.util.ArrayList;

/**
 * Created by Андрей on 17.10.2017.
 */

public class Model implements ModelInterface{

    private InsectsDbManager dbManager;
    private InsectImageLoader imageLoader;

    public Model() {
     dbManager = new InsectsDbManager(AppBugMaster.getContext());
        imageLoader = new InsectImageLoader(AppBugMaster.getContext());
    }

    @Override
    public ArrayList<Insect> loadData() {
        return dbManager.loadInsects();
    }

    @Override
    public Question createQuestion() {
        Question quizQuestion = new Question();
        quizQuestion.prepareQuestion(loadData());

        return quizQuestion;
    }

    @Override
    public Bitmap loadInsectImage(String imageAsset) {
        return imageLoader.loadImage(imageAsset);
    }

}
