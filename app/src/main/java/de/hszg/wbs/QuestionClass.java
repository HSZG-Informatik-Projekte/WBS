package de.hszg.wbs;

import java.util.ArrayList;

public class QuestionClass {
    private int id;
    private String question;
    private ArrayList<String> anwers;
    private int right;

    QuestionClass(int id, String question, int right, ArrayList<String> anwers) {
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

    public ArrayList<String> getAnwers() {
        return anwers;
    }

    public int getRight() {
        return right;
    }
}
