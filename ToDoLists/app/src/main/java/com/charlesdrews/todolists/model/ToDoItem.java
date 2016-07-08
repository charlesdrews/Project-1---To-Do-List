package com.charlesdrews.todolists.model;

/**
 * Created by charlie on 7/8/16.
 */
public class ToDoItem {
    private String mTitle, mDescription;

    public ToDoItem(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}
