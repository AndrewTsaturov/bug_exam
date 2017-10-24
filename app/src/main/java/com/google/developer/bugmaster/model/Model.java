package com.google.developer.bugmaster.model;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.AppBugMaster;
import com.google.developer.bugmaster.model.data.InsectImageLoader;
import com.google.developer.bugmaster.model.data.InsectsDbManager;
import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;

import java.util.ArrayList;

/**
 * Created by Андрей on 17.10.2017.
 */

public class Model implements ModelInterface{


    public Model() {

    }

    @Override
    public ArrayList<Insect> loadData() {
        InsectsDbManager dbManager = new InsectsDbManager(AppBugMaster.getContext());
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
        InsectImageLoader imageLoader = new InsectImageLoader(AppBugMaster.getContext());
        return imageLoader.loadImage(imageAsset);
    }

}
