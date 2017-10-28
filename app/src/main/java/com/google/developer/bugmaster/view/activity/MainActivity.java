package com.google.developer.bugmaster.view.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.model.AppBugMaster;
import com.google.developer.bugmaster.presenters.AppPresenter;
import com.google.developer.bugmaster.presenters.Presenter;
import com.google.developer.bugmaster.view.AppView;
import com.google.developer.bugmaster.view.adapters.InsectRecyclerAdapter;
import com.google.developer.bugmaster.view.fragments.InsectDetailsFragment;
import com.google.developer.bugmaster.view.fragments.InsectListFragment;
import com.google.developer.bugmaster.view.fragments.QuizFragment;
import com.google.developer.bugmaster.view.fragments.SettingsFragment;


//TODO: можно ли реализовывать вью в активити?
//FIXME переделать фрагменты добавить tollbar
public class MainActivity extends AppCompatActivity implements AppView {

    public static byte screenId;

    AppPresenter presenter;

    AppBugMaster ap;

    InsectRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ap = (AppBugMaster) getApplicationContext();

            presenter = new Presenter(ap.getModel(), this);

        //FIXME: неудачная попытка сохранения состояния списка
        adapter = (InsectRecyclerAdapter) getLastCustomNonConfigurationInstance();

        if(adapter == null){
            adapter = new InsectRecyclerAdapter(presenter);
        }

        presenter.onAttach();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackButtonClick();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();

        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return adapter;
    }

    //View implemetation

    @Override
        public void showInsectList() {
            setScreenId(MAIN_SCREEN_ID);

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);

            InsectListFragment listFragment = new InsectListFragment();
            listFragment.setPresenter(presenter);
            listFragment.setAdapter(adapter);

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_layout, listFragment).
                    commit();
        }

    @Override
        public void showInsectDetails() {
        setScreenId(DETAILS_SCREEN_ID);

            InsectDetailsFragment detailsFragment = new InsectDetailsFragment();
            detailsFragment.setPresenter(presenter);

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_layout, detailsFragment)
                    .commit();
        }

    @Override
        public void showQuiz() {
        setScreenId(QUIZ_SCREEN_ID);

            QuizFragment quizFragment = new QuizFragment();
            quizFragment.setPresenter(presenter);

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_layout, quizFragment)
                    .commit();
        }

    @Override
        public void showSettings() {
        setScreenId(SETTINGS_SCREEN_ID);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

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

    @Override
    public void setScreenId(byte screenId) {
        this.screenId = screenId;
    }

    @Override
    public byte getScreenId() {
        return screenId;
    }

}
