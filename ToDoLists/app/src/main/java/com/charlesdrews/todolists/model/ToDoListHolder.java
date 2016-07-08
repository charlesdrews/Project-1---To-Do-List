package com.charlesdrews.todolists.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlie on 7/8/16.
 */
public class ToDoListHolder {
    private static ToDoListHolder sInstance;
    private List<ToDoList> mToDoLists;

    public static ToDoListHolder getInstance() {
        if (sInstance == null) {
            sInstance = new ToDoListHolder();
        }
        return sInstance;
    }

    private ToDoListHolder() {
        mToDoLists = new ArrayList<>();
    }

    public List<ToDoList> getToDoLists() {
        return mToDoLists;
    }

    public ToDoList getToDoListByPosition(int position) {
        return mToDoLists.get(position);
    }

    public boolean addToDoList(ToDoList toDoList) {
        return mToDoLists.add(toDoList);
    }

    public boolean removeToDoList(ToDoList toDoList) {
        return mToDoLists.remove(toDoList);
    }
}
