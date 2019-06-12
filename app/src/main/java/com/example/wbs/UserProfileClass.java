package com.example.wbs;

import android.graphics.Color;
import java.io.Serializable;

public class UserProfileClass implements Serializable  { // extends Activity

    private String name;
    private Gender gender;
    private int age;
    private int follower;
    private Color color;
    private boolean isProfile;

    UserProfileClass() {
        this.isProfile = false;

        //saveToFile();
    }

    private void saveToFile() {
        JsonUtil.WBSProfileToJson(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getisProfile() {
        return isProfile;
    }

    public void setisProfile(boolean profile) {
        isProfile = profile;
    }

    public enum Gender {
        MALE, FEMALE;
    };
}
