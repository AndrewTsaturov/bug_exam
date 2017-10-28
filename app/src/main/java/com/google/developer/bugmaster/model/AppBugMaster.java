package com.google.developer.bugmaster.model;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.google.developer.bugmaster.model.repositories.AppRepository;

/**
 * Created by Дом on 04.10.2017.
 */
//TODO: вчера серьезно пытался передлать проект с добавлением репозитория, старый презентер отчистил он был негоден
    //TODO интересует как правильно использовать обьект repository;
    //TODO будет добавлен DI
public class AppBugMaster extends Application {

    private static String ALARM_RECIEVER_ACION = "com.google.developer.bugmaster.UPDATE_REMINDER";

    private AppRepository repository;

    public AppModel model;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = new AppRepository(getApplicationContext());

        model =  new Model(repository);
        model.loadData();
    }

    public AppModel getModel() {
        return model;
    }

    public void setModel(AppModel model) {
        this.model = model;
    }
}
