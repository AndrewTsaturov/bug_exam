package com.google.developer.bugmaster;

import android.app.Application;
import android.util.Log;

import com.google.developer.bugmaster.data.DbManager;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.utils.Question;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Дом on 04.10.2017.
 */

public class AppBugMaster extends Application {

    public static Question quizQuestion;
    public static ArrayList<Insect> insectsList = new ArrayList<>();
    public static int insectListChoosenPosition = Integer.MIN_VALUE, quizFragmentChoosenAnswer = Integer.MIN_VALUE;

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

    public Question prepareQuestionForQuizFragment(){
        Question  question = new Question();
        question.createQuestion(insectsList, insectsList.size() - 1);

        return question;
    }

    private void loadData(){
        DbManager manager = new DbManager(getApplicationContext());

        insectsList = manager.loadInsects();
        Log.d("insectsListSize", "данные загружены");
    }

}
