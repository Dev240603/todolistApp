package com.example.to_do_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private List<Task> tasks;
    private TaskDeleteListener deleteListener;

    public interface TaskDeleteListener {
        void onDeleteTask(int position);
    }

    public TaskAdapter(Context context, List<Task> tasks, TaskDeleteListener listener) {
        super(context, 0, tasks);
        this.context = context;
        this.tasks = tasks;
        this.deleteListener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.task_title);
        CheckBox completedCheckBox = convertView.findViewById(R.id.task_completed);
        ImageButton deleteButton = convertView.findViewById(R.id.delete_button);

        titleTextView.setText(task.getTitle());
        completedCheckBox.setChecked(task.isCompleted());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteListener != null) {
                    deleteListener.onDeleteTask(position);
                }
            }
        });

        return convertView;
    }
}