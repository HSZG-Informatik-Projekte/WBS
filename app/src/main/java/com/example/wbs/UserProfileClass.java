package com.example.wbs;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import java.io.Serializable;

public class UserProfileClass implements Serializable  { // extends Activity

    private String name;
    private Gender gender;
    private int age;
    private int follower;
    private Color color;
    private boolean isProfile;

    //private SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);

    UserProfileClass() {
        this.isProfile = false;
        //setName(getStoreValues("NAME","ERROR"));
        //setGender((Gender) getStoreValues("GENDER","ERROR"));
        //setAge(getStoreValues("AGE", 0));
        //setFollower(getStoreValues("FOLLOWER", 0));
        //setColor(Color.toArgb(getStoreValues("COLOR","#000000")));
    }

    //SET STRING
    public void setStoreValues(String key, String value) {
/*        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(key, value);
        edit.commit();
        edit.apply();
  */  }
    //GET STRING
    public String getStoreValues(String key, String def) {
 //       return app_preferences.getString(key, def);
        return "";
    }

    //SET INTEGER
    public void setStoreValues(String key, Integer value) {
  /*      SharedPreferences.Editor edit = app_preferences.edit();
        edit.putInt(key, value);
        edit.commit();
        edit.apply();
  */  }
    //GET INTEGER
    public Integer getStoreValues(String key, Integer def) {
  //      return app_preferences.getInt(key, def);
        return 0;
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
