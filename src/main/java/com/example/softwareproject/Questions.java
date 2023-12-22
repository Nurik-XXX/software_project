package com.example.softwareproject;

public class Questions {
    public String question;
    public String[] answers;

    public Questions(String questions, String[] answers){
        this.question = questions;
        this.answers = answers;
    }
    public String correctAnswer(){
        return this.answers[answers.length - 1];
    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(String question){
        this.question = question;
    }
    public void setAnswers(String[] answers){
        this.answers = answers;
    }
    public String[] getAnswers() {
        return answers;
    }
}
