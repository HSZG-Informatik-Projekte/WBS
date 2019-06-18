package com.example.wbs;

import java.util.ArrayList;

public class QuestionClass {
    private int id;
    private String question;
    private ArrayList<String> anwers;
    private String right;

    QuestionClass(int id, String question, String right, ArrayList<String> anwers) {
        this.id = id;
        this.question = question;
        this.right = right;
        this.anwers = anwers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnwers() {
        return anwers;
    }

    public void setAnwers(ArrayList<String> anwers) {
        this.anwers = anwers;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
