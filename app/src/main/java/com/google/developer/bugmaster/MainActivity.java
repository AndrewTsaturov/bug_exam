package com.google.developer.bugmaster;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;


import com.google.developer.bugmaster.ui.FragmentInterface;
import com.google.developer.bugmaster.ui.InsectDetailsFragment;
import com.google.developer.bugmaster.ui.InsectListFragment;
import com.google.developer.bugmaster.ui.QuizFragment;
import com.google.developer.bugmaster.ui.SettingsFragment;


public class MainActivity extends AppCompatActivity implements FragmentInterface {

    private InsectDetailsFragment insectDetailsFragment;
    private InsectListFragment insectListFragment;
    private QuizFragment quizFragment;
    private SettingsFragment settingsFragment;

    private static byte screenId;

    boolean checkQuizLaunch;

    boolean sortFlag;

    //overriding native activity callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIntentMessageFromReminder();

        setScreenToAttach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) listScreenLaunch();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (screenId == MAIN_SCREEN_ID)
            super.onBackPressed();

        else listScreenLaunch();
    }

    //Implementing FragmentInterface class
    @Override
    public void sortInsectList() {
        AppBugMaster ap = ((AppBugMaster) getApplicationContext());

        if (!sortFlag) {
            ap.sortInsectList(false);
            setSortFlag(true);
        } else {
            ap.sortInsectList(true);
            setSortFlag(false);
        }

        listScreenLaunch();
    }

    @Override
    public void detailsScreenLaunch(int position) {
        AppBugMaster ap = (AppBugMaster) getApplicationContext();
        ap.setInsectListChoosenPosition(position);

        insectDetailsFragment = new InsectDetailsFragment();
        insectDetailsFragment.setInsect(ap.getInsectsList().get(position));
        insectDetailsFragment.setActionBar(getSupportActionBar());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, insectDetailsFragment)
                .commit();

        setScreenId(DETAILS_SCREEN_ID);
    }

    @Override
    public void settingsScreenLaunch() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        settingsFragment = new SettingsFragment();
        settingsFragment.setActionBar(getSupportActionBar());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, settingsFragment)
                .commit();

        setScreenId(SETTINGS_SCREEN_ID);
    }

    @Override
    public void quizScreenLaunch() {
        AppBugMaster ap = (AppBugMaster) getApplicationContext();


        if (ap.getQuizQuestion() == null) {
            ap.prepareQuestionForQuizFragment();
        }

        quizFragment = new QuizFragment();
        quizFragment.setQuestion(ap.getQuizQuestion());
        quizFragment.setActionBar(getSupportActionBar());

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_layout, quizFragment)
                .commit();

        setScreenId(QUIZ_SCREEN_ID);
    }

    @Override
    public void listScreenLaunch() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        setAppParamsToDefault();

        insectListFragment = new InsectListFragment();
        insectListFragment.setListOfInsects(((AppBugMaster)
                getApplicationContext()).getInsectsList());
        insectListFragment.setActionBar(getSupportActionBar());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, insectListFragment)
                .commit();

        setScreenId(MAIN_SCREEN_ID);
    }

    private void setScreenToAttach() {
        AppBugMaster ap = (AppBugMaster) getApplicationContext();

        switch (screenId) {
            case MAIN_SCREEN_ID:
                listScreenLaunch();
                break;

            case DETAILS_SCREEN_ID:
                detailsScreenLaunch(ap.getInsectListChoosenPosition());
                break;

            case QUIZ_SCREEN_ID:
                quizScreenLaunch();
                break;

            case SETTINGS_SCREEN_ID:
                settingsScreenLaunch();
                break;

            default:
                listScreenLaunch();
                break;
        }
    }

    public static void setScreenId(byte screenId) {
        MainActivity.screenId = screenId;
    }

    public void setSortFlag(boolean sortFlag) {
        this.sortFlag = sortFlag;
    }

    private void getIntentMessageFromReminder() {
        checkQuizLaunch = getIntent().getBooleanExtra(INTENT_QUIZ_SCREEN_LAUNCH, false);

        if (checkQuizLaunch) {
            setScreenId(QUIZ_SCREEN_ID);

            AppBugMaster ap = ((AppBugMaster) getApplicationContext());

            ap.prepareQuestionForQuizFragment();
        }
    }

    private void setAppParamsToDefault() {
        AppBugMaster ap = ((AppBugMaster) getApplicationContext());

        ap.setInsectListChoosenPosition(Integer.MIN_VALUE);
        ap.setQuizFragmentChoosenAnswer(Integer.MIN_VALUE);
        ap.setQuizQuestion(null);
    }
}
