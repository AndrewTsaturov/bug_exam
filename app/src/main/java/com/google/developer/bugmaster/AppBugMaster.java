package com.google.developer.bugmaster;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.google.developer.bugmaster.model.repositories.AppRepository;

/**
 * Created by Дом on 04.10.2017.
 */
//TODO: вчера серьезно пытался передлать проект с добавлением репозитория, старый презентер отчистил он был негоден
    //TODO интересует как правильно использовать оьект repository;
public class AppBugMaster extends Application {

    private static String ALARM_RECIEVER_ACION = "com.google.developer.bugmaster.UPDATE_REMINDER";

    public static AppRepository repository;



    @Override
    public void onCreate() {
        super.onCreate();
        repository = new AppRepository();
    }

    public static AppRepository getRepository() {
        return repository;
    }


    //Todo перенести броадкаст в презентер
    public void sendReminderBradcast(){
        Intent intent = new Intent();
        intent.setAction(ALARM_RECIEVER_ACION);
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
}
