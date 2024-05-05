package com.example.fixcar20;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {

    private String answerRight;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    List<String> onlineAnswers1;
    List<String> onlineAnswers2;
    List<String> onlineAnswers3;
    List<String> onlineAnswers4;
    List<String> questions;
    List<String> correctAnswers;
    List<String> onlineImages;
    private String imageURL;
    private String countryName;
    private String questionID;


    String username;


    public QuestionModel() {
    }


    public QuestionModel(String answerRight, String answer1, String answer2, String answer3, String answer4, String imageURL, String countryName, String questionID) {
        this.answerRight = answerRight;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.imageURL = imageURL;
        this.countryName = countryName;
        this.questionID = questionID;
    }

    public QuestionModel(String imageURL, String countryName, String questionID, String username) {
        this.imageURL = imageURL;
        this.countryName = countryName;
        this.questionID = questionID;
        this.username = username;
    }

    public QuestionModel(List<String> onlineAnswers1, List<String> onlineAnswers2, List<String> onlineAnswers3, List<String> onlineAnswers4, List<String> onlineImages, String countryName, String username, List<String> questions, List<String> correctAnswers) {
        this.onlineAnswers1 = onlineAnswers1;
        this.onlineAnswers2 = onlineAnswers2;
        this.onlineAnswers3 = onlineAnswers3;
        this.onlineAnswers4 = onlineAnswers4;
        this.onlineImages = onlineImages;
        this.countryName = countryName;
        this.username = username;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
    }


    public String getAnswerRight() {
        return answerRight;
    }

    public void setAnswerRight(String answerRight) {
        this.answerRight = answerRight;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
