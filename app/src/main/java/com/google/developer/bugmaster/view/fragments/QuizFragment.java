package com.google.developer.bugmaster.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.developer.bugmaster.view.activity.MainActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.model.pojo.Question;
import com.google.developer.bugmaster.presenters.Presenter;
import com.google.developer.bugmaster.presenters.PresenterInterface;
import com.google.developer.bugmaster.view.custom.AnswerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Андрей on 19.09.2017.
 */

public class QuizFragment extends Fragment implements AnswerView.OnAnswerSelectedListener {

    @BindView(R.id.quiz_text_question) TextView questionTextView;
    @BindView(R.id.quiz_answer_select) AnswerView answerView;
    @BindView(R.id.quiz_text_correct) TextView answerCorrectTextView;

    Unbinder unbinder;

    Question question;

    PresenterInterface presenter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_quiz, container, false);

        unbinder = ButterKnife.bind(this, fragmentView);

        setupView();

        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        else throw new RuntimeException("ActionBar Device Conflict!");

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) presenter.onBackButtonClick();

        return super.onOptionsItemSelected(item);
    }

    //AnswerView interface implement

    @Override
    public void onCorrectAnswerSelected() {
        answerCorrectTextView.setTextColor(getResources().getColor(R.color.colorCorrect));
        answerCorrectTextView.setText(getString(R.string.answer_correct));

        presenter.onSelectedAnswerListener(answerView.getCheckedIndex());
    }

    @Override
    public void onWrongAnswerSelected() {
        answerCorrectTextView.setTextColor(getResources().getColor(R.color.colorWrong));
        answerCorrectTextView.setText(getString(R.string.answer_wrong));

        presenter.onSelectedAnswerListener(answerView.getCheckedIndex());
    }

    public void setupView(){
        questionTextView.setText(getString(R.string.question_text) + question.getQuestionSubject());

        answerView.setOnAnswerSelectedListener(this);
        answerView.loadAnswers(question.getAnswerOptions(), question.getCorrectAnswer());


        if(presenter.getChoosenAnswerIndex() != presenter.CHOSEN_ANSWER_INDEX_DEFAULT)
            answerView.setCheckedIndex(presenter.getChoosenAnswerIndex());
    }


    public void setQuestion(Question question) {
        this.question = question;
    }

    public PresenterInterface getPresenter() {
        return presenter;
    }

    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
    }
}
