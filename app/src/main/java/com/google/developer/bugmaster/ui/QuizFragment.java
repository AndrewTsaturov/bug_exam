package com.google.developer.bugmaster.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.developer.bugmaster.MainActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.utils.Question;
import com.google.developer.bugmaster.views.AnswerView;

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

    FragmentInterface fragmentInterface;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentInterface((MainActivity) getActivity());
    }

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
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) fragmentInterface.listScreenLaunch();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCorrectAnswerSelected() {
        answerCorrectTextView.setTextColor(getResources().getColor(R.color.colorCorrect));
        answerCorrectTextView.setText(getString(R.string.answer_correct));
    }

    @Override
    public void onWrongAnswerSelected() {
        answerCorrectTextView.setTextColor(getResources().getColor(R.color.colorWrong));
        answerCorrectTextView.setText(getString(R.string.answer_wrong));
    }

    public void setupView(){
        questionTextView.setText(getString(R.string.question_text) + question.getQuestionSubject());

        answerView.loadAnswers(question.getAnswerOptions(), question.getCorrectAnswer());
    }

    private void setFragmentInterface(MainActivity mainActivity){
        fragmentInterface = mainActivity;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
