package com.google.developer.bugmaster;

import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.google.developer.bugmaster.data.DatabaseManager;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.ui.FragmentInterface;
import com.google.developer.bugmaster.ui.InsectDetailsFragment;
import com.google.developer.bugmaster.ui.InsectListFragment;
import com.google.developer.bugmaster.ui.QuizFragment;
import com.google.developer.bugmaster.ui.SettingsFragment;
import com.google.developer.bugmaster.utils.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentInterface {


    private ArrayList<Insect> insectsList;
    private DatabaseManager manager;
    private FragmentTransaction fragmentTransaction;

    private InsectDetailsFragment detailsScreen;
    private InsectListFragment mainScreen;
    private QuizFragment quizScreen;
    private SettingsFragment setteingsSreen;

    private byte MAIN_SCREEN_ID = 1;
    private byte DETAILS_SCREEN_ID = 2;
    private byte QUIZ_SCREEN_ID = 3;
    private byte SETTINGS_SCREEN_ID = 4;

    private byte screenId = 1;

    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        initUI();

    }

    @Override
    public void sortInsectList() {
        Comparator<Insect> comparator = new Insect.CommonNameComparator().
                thenComparing(new Insect.DangerLevelComparator());

        Collections.sort(insectsList, comparator);

        listScreenLaunch();
    }

    @Override
    public void detailsScreenLaunch(int position) {
        detailsScreen = new InsectDetailsFragment();
        detailsScreen.setInsect(insectsList.get(position));

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, detailsScreen);
        fragmentTransaction.commit();

        setScreenId(DETAILS_SCREEN_ID);
    }

    @Override
    public void settingsScreenLaunch() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, setteingsSreen);
        fragmentTransaction.commit();

        setScreenId(SETTINGS_SCREEN_ID);
    }

    @Override
    public void quizScreenLaunch() {
        question = new Question();
        question.createQuestion(insectsList, question.getRandomIndex(insectsList.size()));

        quizScreen = new QuizFragment();
        quizScreen.setQuestion(question);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, quizScreen);
        fragmentTransaction.commit();
    }

    @Override
    public void listScreenLaunch() {
        mainScreen = new InsectListFragment();
        mainScreen.setListOfInsects(insectsList);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, mainScreen);
        fragmentTransaction.commit();

        setScreenId(MAIN_SCREEN_ID);
    }

    private void initUI(){
        ButterKnife.bind(this);

        mainScreen = new InsectListFragment();
        detailsScreen = new InsectDetailsFragment();
        quizScreen = new QuizFragment();
        setteingsSreen = new SettingsFragment();

        chooseScreenToAttach();
    }

    private void loadData(){

        manager = new DatabaseManager(getApplicationContext());

        insectsList = manager.loadInsects();
    }

    private void chooseScreenToAttach(){
        switch (screenId){
            case 1:
                listScreenLaunch();
                break;
            case 2:
                detailsScreenLaunch(mainScreen.getPosition());
                break;
            case 3:
                quizScreenLaunch();
                break;
            case 4:
                settingsScreenLaunch();
                break;
        }
    }

    public void setScreenId(byte screenId) {
        this.screenId = screenId;
    }
}
