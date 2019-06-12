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
        //setName(getStoreValues("NAME","ERROR"));
        //setGender((Gender) getStoreValues("GENDER","ERROR"));
        //setAge(getStoreValues("AGE", 0));
        //setFollower(getStoreValues("FOLLOWER", 0));
        //setColor(Color.toArgb(getStoreValues("COLOR","#000000")));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setStoreValues("NAME", name);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
        setStoreValues("GENDER", gender.toString());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        setStoreValues("AGE", age);
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
        setStoreValues("FOLLOWER", follower);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setStoreValues("COLOR", color.toString());
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
