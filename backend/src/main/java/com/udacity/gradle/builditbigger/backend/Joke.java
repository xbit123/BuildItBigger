package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class Joke {

    private String text;

    public String getData() {
        return text;
    }

    public void setData(String data) {
        text = data;
    }
}