package com.google.developer.bugmaster;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.data.DbManager;
import com.google.developer.bugmaster.reminders.AlarmReceiver;
import com.google.developer.bugmaster.utils.Question;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Дом on 04.10.2017.
 */

public class AppBugMaster extends Application {

    private static String ALARM_RECIEVER_ACION = "com.google.developer.bugmaster.UPDATE_REMINDER";

    public ArrayList<Insect> insectsList = new ArrayList<>();

    public Question quizQuestion;

    public int insectListChoosenPosition = Integer.MIN_VALUE;

    public static int quizFragmentChoosenAnswer = Integer.MIN_VALUE;

    AlarmReceiver alarmReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        loadData();
    }

    public void sortInsectList(boolean sortFlag){
        if(sortFlag)
            Collections.sort(insectsList, new Insect.DangerLevelComparator());
        else
            Collections.sort(insectsList, new Insect.CommonNameComparator());
    }

    public void prepareQuestionForQuizFragment(){
        Question  question = new Question();
        question.createQuestion(insectsList);

        setQuizQuestion(question);
    }

    private void loadData(){
        DbManager manager = new DbManager(getApplicationContext());

        setInsectsList(manager.loadInsects());

        Log.d("insectsListSize", "данные загружены");
    }

    public void sendReminderBradcast(){
        Intent intent = new Intent();
        intent.setAction(ALARM_RECIEVER_ACION);
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

    public ArrayList<Insect> getInsectsList() {
        return insectsList;
    }

    public Question getQuizQuestion() {
        return quizQuestion;
    }

    public int getInsectListChoosenPosition() {
        return insectListChoosenPosition;
    }

    public int getQuizFragmentChoosenAnswer() {
        return quizFragmentChoosenAnswer;
    }

    public void setInsectsList(ArrayList<Insect> insectsList) {
        this.insectsList = insectsList;
    }

    public void setQuizQuestion(Question quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public void setInsectListChoosenPosition(int insectListChoosenPosition) {
        this.insectListChoosenPosition = insectListChoosenPosition;
    }

    public void setQuizFragmentChoosenAnswer(int quizFragmentChoosenAnswer) {
        this.quizFragmentChoosenAnswer = quizFragmentChoosenAnswer;
    }
}
