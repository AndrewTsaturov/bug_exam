package com.google.developer.bugmaster;

import android.app.Application;
import android.util.Log;

import com.google.developer.bugmaster.data.DatabaseManager;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.utils.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Дом on 04.10.2017.
 */

public class AppBugMaster extends Application {

    public static ArrayList<Insect> insectsList = new ArrayList<>();

    public static Question quizQuestion;

    public static int insectListChoosenPosition = Integer.MIN_VALUE, quizFragmentChoosenAnswer = Integer.MIN_VALUE;

    @Override
    public void onCreate() {
        super.onCreate();

        loadData();

    }

    public void sortInsectList(){
        Comparator<Insect> comparator = new Insect.CommonNameComparator().
                thenComparing(new Insect.DangerLevelComparator());

        Collections.sort(insectsList, comparator);
    }

    public Question prepareQuestionForQuizFragment(){
        Question  question = new Question();
        question.createQuestion(insectsList, insectsList.size() - 1);

        return question;
    }

    private void loadData(){
        DatabaseManager manager = new DatabaseManager(getApplicationContext());

        insectsList = manager.loadInsects();
        Log.d("insectsListSize", "данные загружены");
    }

}
