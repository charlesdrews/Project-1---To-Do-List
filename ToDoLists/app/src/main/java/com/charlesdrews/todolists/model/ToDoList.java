package com.charlesdrews.todolists.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlie on 7/8/16.
 */
public class ToDoList {
    private String mTitle;
    private List<ToDoItem> mToDoItems;

    public ToDoList(String title) {
        mTitle = title;
        mToDoItems = new ArrayList<>();
    }

    public String getTitle() {
        return mTitle;
    }

    public List<ToDoItem> getToDoItems() {
        return mToDoItems;
    }

    public boolean addToDoItem(ToDoItem item) {
        return mToDoItems.add(item);
    }

    public boolean removeToDoItem(ToDoItem item) {
        return mToDoItems.remove(item);
    }
}
