package com.example.wbs;

public class UserProfileClass  {

    private String name;
    private Gender gender;
    private int age;
    private int follower;
    private String color;
    private boolean isProfile;
    private String action;

    UserProfileClass() {
        this.isProfile = false;
    }

    UserProfileClass(String name, Gender gender, int age, int follower, String color) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.follower = follower;
        this.color = color;
        this.isProfile = true;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public enum Gender {
        MALE, FEMALE;

        public static Gender getGender(String s) {
            return s == MALE.toString() ? MALE : FEMALE;
        }
    };
}
