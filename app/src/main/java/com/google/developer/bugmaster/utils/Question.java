package com.google.developer.bugmaster.utils;


import com.google.developer.bugmaster.data.Insect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Андрей on 24.09.2017.
 */

public class Question {

    private String questionSubject, correctAnswer;

    private List<String> answerOptions;


    int ANSWER_OPTIONS_COUNT = 5;


    public Question() {
    }

    public void setQuestionSubject(String questionSubject) {
        this.questionSubject = questionSubject;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public String getQuestionSubject() {
        return questionSubject;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }


    public void createQuestion(ArrayList<Insect> insectList){
        int randomIndex = getRandomIndex(insectList.size());

        setQuestionSubject(insectList.get(randomIndex).getName());

        setCorrectAnswer(insectList.get(randomIndex).getScientificName());

        setAnswerOptions(fillAnswerOptions(insectList.get(randomIndex).getScientificName(), insectList));
    }

    private List<String> fillAnswerOptions(String correctAnswer, ArrayList<Insect> insectList){
        List<String> result = new ArrayList<>();

        result.add(correctAnswer);

        for (int i = 1; i < ANSWER_OPTIONS_COUNT; i++){
            String optionBuffer = result.get(i - 1);

            while (optionBuffer.equals(result.get(i - 1)))
                optionBuffer = prepareFakeOption(insectList, correctAnswer);

            result.add(optionBuffer);
        }

        Collections.shuffle(result);

        return  result;
    }

    private String prepareFakeOption(ArrayList<Insect> insectList, String correctAnswer){
        String s = correctAnswer;

        while (s.equals(correctAnswer)){
            s = insectList.get(getRandomIndex(insectList.size())).getScientificName();
        }
        return s;
    }

    public int getRandomIndex(int listInsectSize){
        Random r = new Random();
        return  r.nextInt(listInsectSize);
    }


}
