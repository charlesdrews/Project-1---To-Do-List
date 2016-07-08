package com.charlesdrews.todolists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.charlesdrews.todolists.model.ToDoItem;

import java.util.List;

/**
 * Created by charlie on 7/8/16.
 */
public class ToDoItemAdapter extends RecyclerView.Adapter<ToDoItemAdapter.ToDoItemViewHolder> {
    private Context mContext;
    private List<ToDoItem> mToDoItems;

    public ToDoItemAdapter(Context context, List<ToDoItem> toDoItems) {
        mContext = context;
        mToDoItems = toDoItems;
    }

    @Override
    public ToDoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ToDoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoItemViewHolder holder, int position) {
        ToDoItem currentItem = mToDoItems.get(position);
        holder.mTitle.setText(currentItem.getTitle());
        holder.mDescription.setText(currentItem.getDescription());
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mToDoItems.size();
    }

    public class ToDoItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View mContainer;
        TextView mTitle, mDescription;

        public ToDoItemViewHolder(View itemView) {
            super(itemView);
            mContainer = itemView;
            mTitle = (TextView) itemView.findViewById(android.R.id.text1);
            mDescription = (TextView) itemView.findViewById(android.R.id.text2);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
