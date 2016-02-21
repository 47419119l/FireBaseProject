package com.example.sandra.firebaseproject;

/**
 * Created by 47419119l on 03/02/16.
 */
public class User {

    private int birthYear;
    private String fullName;

    public User() {
    }

    public User(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public long getBirthYear() {
        return birthYear;
    }

    public String getFullName() {
        return fullName;
    }


}
