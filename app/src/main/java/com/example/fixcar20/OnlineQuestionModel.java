package com.example.fixcar20;

import java.util.List;

public class OnlineQuestionModel {


    List<String> onlineAnswers1;
    List<String> onlineAnswers2;
    List<String> onlineAnswers3;
    List<String> onlineAnswers4;
    List<String> questions;
    List<String> correctAnswers;
    List<String> onlineImages;

    private String countryName;
    private String questionID;


    String username;


    public OnlineQuestionModel() {
    }






    public OnlineQuestionModel( List<String> correctAnswers, String quizname, List<String> onlineAnswers1, List<String> onlineAnswers2, List<String> onlineAnswers3, List<String> onlineAnswers4, List<String> onlineImages, String questionID, List<String> questions, String username) {
        this.correctAnswers = correctAnswers;
        this.countryName = quizname;
        this.onlineAnswers1 = onlineAnswers1;
        this.onlineAnswers2 = onlineAnswers2;
        this.onlineAnswers3 = onlineAnswers3;
        this.onlineAnswers4 = onlineAnswers4;
        this.onlineImages = onlineImages;
        this.questionID = questionID;
        this.questions = questions;
        this.username = username;



    }



    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getOnlineAnswers1() {
        return onlineAnswers1;
    }

    public void setOnlineAnswers1(List<String> onlineAnswers1) {
        this.onlineAnswers1 = onlineAnswers1;
    }

    public List<String> getOnlineAnswers2() {
        return onlineAnswers2;
    }

    public void setOnlineAnswers2(List<String> onlineAnswers2) {
        this.onlineAnswers2 = onlineAnswers2;
    }

    public List<String> getOnlineAnswers3() {
        return onlineAnswers3;
    }

    public void setOnlineAnswers3(List<String> onlineAnswers3) {
        this.onlineAnswers3 = onlineAnswers3;
    }

    public List<String> getOnlineAnswers4() {
        return onlineAnswers4;
    }

    public void setOnlineAnswers4(List<String> onlineAnswers4) {
        this.onlineAnswers4 = onlineAnswers4;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<String> getOnlineImages() {
        return onlineImages;
    }

    public void setOnlineImages(List<String> onlineImages) {
        this.onlineImages = onlineImages;
    }
}



