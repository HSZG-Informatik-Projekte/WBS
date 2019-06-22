package de.hszg.wbs;

import android.content.res.Resources;

public class UserProfileClass  {

    private String name;
    private Gender gender;
    private int age;
    private int follower;
    private String color;
    private boolean isProfile;
    private String action;
    private int stars;

    UserProfileClass() {
        this.isProfile = false;
    }

    UserProfileClass(String name, Gender gender, int age, int follower, int stars, String color, String action) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.follower = follower;
        this.stars = stars;
        this.color = color;
        this.action = action;
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

    public void setStars(int stars) { this.stars = stars; }

    public void incrementStar() {
        this.stars++;
    }

    public void decrementStar() {
        this.stars--;
    }

    public int getStars() { return stars; }

    public enum Gender {
        MALE(Resources.getSystem().getString(R.string.UPC_MALE)), FEMALE(Resources.getSystem().getString(R.string.UPC_FAMALE));

        private String bezeichner;

        Gender(String bezeichner) {
            this.bezeichner = bezeichner;
        }
        
        public static Gender getGender(String s) {
            return s.equals(MALE.toString()) ? MALE : FEMALE;
        }

        public String getBezeichner() {
            return bezeichner;
        }
    };
}
