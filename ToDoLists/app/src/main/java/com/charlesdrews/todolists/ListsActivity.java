package com.charlesdrews.todolists;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.charlesdrews.todolists.model.ToDoList;
import com.charlesdrews.todolists.model.ToDoListHolder;

public class ListsActivity extends AppCompatActivity {
    private CoordinatorLayout mRootView;
    private ToDoListHolder mToDoListHolder;
    private ToDoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(getString(R.string.lists_activity_title));

        mRootView = (CoordinatorLayout) findViewById(R.id.list_activity_root_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lists_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mToDoListHolder = ToDoListHolder.getInstance();
        mAdapter = new ToDoListAdapter(this, mToDoListHolder.getToDoLists());
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAddListDialog();
            }
        });
    }

    private void launchAddListDialog() {
        final EditText newListTitleInput = new EditText(this);

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.add_list_dialog_title))
                .setView(newListTitleInput)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", null)
                .create();

        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String titleInput = newListTitleInput.getText().toString();
                        if (titleInput.isEmpty()) {
                            newListTitleInput.setError(getString(R.string.blank_title_error_msg));
                        } else {
                            addToDoList(new ToDoList(titleInput));
                            dialog.cancel();
                        }
                    }
                });
    }

    private void addToDoList(final ToDoList toDoList) {
        mToDoListHolder.addToDoList(toDoList);
        mAdapter.notifyDataSetChanged();
        Snackbar.make(mRootView, "List created: " + toDoList.getTitle(), Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeToDoList(toDoList);
                    }
                })
                .show();
    }

    private void removeToDoList(ToDoList toDoList) {
        mToDoListHolder.removeToDoList(toDoList);
        mAdapter.notifyDataSetChanged();
    }
}
