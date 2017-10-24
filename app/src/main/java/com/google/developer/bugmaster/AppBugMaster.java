package com.google.developer.bugmaster;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Дом on 04.10.2017.
 */

public class AppBugMaster extends Application {

    private static String ALARM_RECIEVER_ACION = "com.google.developer.bugmaster.UPDATE_REMINDER";

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    //Todo перенести броадкаст в презентер
    public void sendReminderBradcast(){
        Intent intent = new Intent();
        intent.setAction(ALARM_RECIEVER_ACION);
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
}
