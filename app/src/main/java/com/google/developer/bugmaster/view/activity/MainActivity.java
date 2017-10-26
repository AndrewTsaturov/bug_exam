package com.google.developer.bugmaster.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.model.AppBugMaster;
import com.google.developer.bugmaster.model.pojo.Insect;
import com.google.developer.bugmaster.model.pojo.Question;
import com.google.developer.bugmaster.presenters.AppPresenter;
import com.google.developer.bugmaster.presenters.Presenter;
import com.google.developer.bugmaster.view.AppView;
import com.google.developer.bugmaster.view.fragments.InsectDetailsFragment;
import com.google.developer.bugmaster.view.fragments.InsectListFragment;
import com.google.developer.bugmaster.view.fragments.QuizFragment;
import com.google.developer.bugmaster.view.fragments.SettingsFragment;


//Fixme: можно ли реализовывать вью в активити?
public class MainActivity extends AppCompatActivity implements AppView {

    AppPresenter presenter;

    AppBugMaster ap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ap = (AppBugMaster) getApplicationContext();

            presenter = new Presenter(ap.getModel(), this);

        presenter.onAttach();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
//        presenter.onDetach();
        super.onDestroy();
    }

    @Override
        public void showInsectList() {
            InsectListFragment insectListFragment = new InsectListFragment();
            insectListFragment.setPresenter(presenter);

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_layout, insectListFragment).
                    commit();
        }

    @Override
        public void showInsectDetails(Insect insect, Bitmap insectImage) {
            InsectDetailsFragment insectDetailsFragment = new InsectDetailsFragment();
            insectDetailsFragment.setPresenter(presenter);
            insectDetailsFragment.setInsect(insect);
            insectDetailsFragment.setInsectImage(insectImage);

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_layout, insectDetailsFragment)
                    .commit();
        }

    @Override
        public void showQuiz(Question quizQuestion) {
            QuizFragment quizFragment = new QuizFragment();
            quizFragment.setPresenter(presenter);
            quizFragment.setQuestion(quizQuestion);

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_layout, quizFragment)
                    .commit();
        }

    @Override
        public void showSettings() {
            SettingsFragment settingsFragment = new SettingsFragment();
            settingsFragment.setPresenter(presenter);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_layout, settingsFragment)
                    .commit();
        }

    @Override
        public void stopView() {
          this.finish();
        }

}
