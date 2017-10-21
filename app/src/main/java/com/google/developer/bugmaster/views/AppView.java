package com.google.developer.bugmaster.views;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.model.Insect;
import com.google.developer.bugmaster.model.Question;
import com.google.developer.bugmaster.presenters.Presenter;
import com.google.developer.bugmaster.presenters.PresenterInterface;
import com.google.developer.bugmaster.ui.InsectDetailsFragment;
import com.google.developer.bugmaster.ui.InsectListFragment;
import com.google.developer.bugmaster.ui.QuizFragment;
import com.google.developer.bugmaster.ui.SettingsFragment;

/**
 * Created by Андрей on 18.10.2017.
 */

public class AppView implements ViewInterface  {
    //ToDo create fields fo
    private InsectListFragment insectListFragment;
    private InsectDetailsFragment insectDetailsFragment;
    private QuizFragment quizFragment;
    private SettingsFragment settingsFragment;

    private PresenterInterface presenter = new Presenter();

    private FragmentManager fm;


    public AppView() {
       presenter.onAttach();
    }

    public AppView(FragmentManager supportedFragmentManager) {
        presenter.onAttach();

        fm = supportedFragmentManager;
    }

    @Override
    public void showInsectList() {
        insectListFragment = new InsectListFragment();

                fm.
                beginTransaction().
                replace(R.id.fragment_layout, insectListFragment)
                .commit();
    }

    @Override
    public void showInsectDetails(Insect insect, Bitmap insectImage) {
          insectDetailsFragment = new InsectDetailsFragment();
          insectDetailsFragment.setInsect(insect);
          insectDetailsFragment.setInsectImage(insectImage);


                fm.
                beginTransaction().
                replace(R.id.fragment_layout, insectDetailsFragment).
                commit();
    }

    @Override
    public void showQuiz(Question quizQuestion) {
        quizFragment = new QuizFragment();
        quizFragment.setQuestion(quizQuestion);

        fm.beginTransaction()
                .replace(R.id.fragment_layout, quizFragment)
                .commit();
    }

    @Override
    public void showSettings() {
        settingsFragment = new SettingsFragment();

        fm.beginTransaction().
                replace(R.id.fragment_layout, settingsFragment)
                .commit();
    }


    @Override
    public void stopView() {
        presenter.onDetach();
    }

}
