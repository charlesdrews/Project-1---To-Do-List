package com.charlesdrews.todolists;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charlesdrews.todolists.model.ToDoList;

import java.util.List;

/**
 * Created by charlie on 7/8/16.
 */
public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder> {
    public static final String SELECTED_LIST_KEY = "selected_list_key";

    private Context mContext;
    private List<ToDoList> mToDoLists;

    public ToDoListAdapter(Context context, List<ToDoList> toDoLists) {
        mContext = context;
        mToDoLists = toDoLists;
    }

    @Override
    public ToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ToDoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ToDoListViewHolder holder, final int position) {
        ToDoList toDoList = mToDoLists.get(position);
        holder.mTextView.setText(toDoList.getTitle());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ItemsActivity.class);
                intent.putExtra(SELECTED_LIST_KEY, position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mToDoLists.size();
    }

    public class ToDoListViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ToDoListViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
