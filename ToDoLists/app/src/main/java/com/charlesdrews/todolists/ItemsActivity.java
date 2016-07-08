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
import android.widget.LinearLayout;

import com.charlesdrews.todolists.model.ToDoItem;
import com.charlesdrews.todolists.model.ToDoList;
import com.charlesdrews.todolists.model.ToDoListHolder;

public class ItemsActivity extends AppCompatActivity {
    private CoordinatorLayout mRootView;
    private ToDoList mSelectedList;
    private ToDoItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int selectedListPosition = getIntent().getIntExtra(ToDoListAdapter.SELECTED_LIST_KEY, -1);
        if (selectedListPosition == -1) {
            finish();
        }

        mRootView = (CoordinatorLayout) findViewById(R.id.items_activity_root_view);

        mSelectedList = ToDoListHolder.getInstance().getToDoListByPosition(selectedListPosition);

        toolbar.setTitle(mSelectedList.getTitle());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.items_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new ToDoItemAdapter(this, mSelectedList.getToDoItems());
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAddItemDialog();
            }
        });
    }

    private void launchAddItemDialog() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final EditText itemTitleInput = new EditText(this);
        itemTitleInput.setHint("Item title");
        linearLayout.addView(itemTitleInput);

        final EditText itemDescInput = new EditText(this);
        itemDescInput.setHint("Item description");
        linearLayout.addView(itemDescInput);

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add item to " + mSelectedList.getTitle())
                .setView(linearLayout)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", null)
                .create();

        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String titleInput = itemTitleInput.getText().toString();
                        String descInput = itemDescInput.getText().toString();

                        if (titleInput.isEmpty()) {
                            itemTitleInput.setError(getString(R.string.blank_title_error_msg));
                        } else {
                            addToDoItem(new ToDoItem(titleInput, descInput));
                            dialog.cancel();
                        }
                    }
                });
    }

    private void addToDoItem(final ToDoItem toDoItem) {
        mSelectedList.addToDoItem(toDoItem);
        mAdapter.notifyDataSetChanged();
        Snackbar.make(mRootView, "Item created: " + toDoItem.getTitle(), Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeToDoItem(toDoItem);
                    }
                })
                .show();
    }

    private void removeToDoItem(ToDoItem toDoItem) {
        mSelectedList.removeToDoItem(toDoItem);
        mAdapter.notifyDataSetChanged();
    }
}
