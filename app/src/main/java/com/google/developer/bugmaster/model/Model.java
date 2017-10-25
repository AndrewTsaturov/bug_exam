package com.google.developer.bugmaster.model;

import android.graphics.Bitmap;

import com.google.developer.bugmaster.AppBugMaster;
import com.google.developer.bugmaster.model.data.InsectImageLoader;
import com.google.developer.bugmaster.model.data.InsectsDbManager;
import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;
import com.google.developer.bugmaster.model.repositories.AppRepository;

import java.util.ArrayList;

/**
 * Created by Андрей on 17.10.2017.
 */
//TODO: я полностю переопределю модель после того как ты скажешь мне как правильно хранить данные в репозиториях

public class Model implements ModelInterface{

//Fixme:  тут можно проставить поле репозитория?

    public Model() {
//Fixme что-то что связывет на с репоторием?
    }

//Fixme: переопределить после решения вопроса по хранению данных
    @Override
    public void loadData() {

    }

    @Override
    public void createQuestion() {

    }

    @Override
    public void loadInsectImage(String imageAsset) {

    }
}
