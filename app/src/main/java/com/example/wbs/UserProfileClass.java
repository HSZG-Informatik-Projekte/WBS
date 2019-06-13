package com.example.wbs;

import android.content.Context;
import java.io.Serializable;

public class UserProfileClass implements Serializable  { // extends Activity

    private String name;
    private Gender gender;
    private int age;
    private int follower;
    private String color;
    private boolean isProfile;

    UserProfileClass(String name,Gender gender,int age,int follower,String color) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.follower = follower;
        this.color = color;
    }

    UserProfileClass(Context context) {
        UserProfileClass tmp = JsonUtil.readProfileFromJson(context);
        if ( tmp != null) {
            this.name = tmp.getName();
            this.gender = tmp.getGender();
            this.age = tmp.getAge();
            this.follower = tmp.getFollower();
            this.color = tmp.getColor();
            this.isProfile = true;
        } else {
            this.isProfile = false;
        }
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
