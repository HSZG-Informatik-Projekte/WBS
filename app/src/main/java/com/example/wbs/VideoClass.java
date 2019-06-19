package com.example.wbs;

public class VideoClass {
    private int id;
    private String name;
    private String description;
    private String filepath;
    private int duration;
    private int questionid;

    VideoClass(int id, String name, String description, String filepath, int duration, int questionid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.filepath = filepath;
        this.duration = duration;
        this.questionid = questionid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }
}
