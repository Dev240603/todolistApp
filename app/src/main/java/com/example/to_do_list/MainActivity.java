package com.example.to_do_list;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskDeleteListener {
    private EditText taskInput;
    private Button addButton;
    private ListView taskList;
    private List<Task> tasks;
    private TaskAdapter adapter;
    private TaskDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.task_input);
        addButton = findViewById(R.id.add_button);
        taskList = findViewById(R.id.task_list);

        database = new TaskDatabase(this);
        tasks = database.getAllTasks();

        adapter = new TaskAdapter(this, tasks, this);
        taskList.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitle = taskInput.getText().toString();
                if (!taskTitle.isEmpty()) {
                    Task newTask = new Task(taskTitle);
                    database.addTask(newTask);
                    tasks.add(newTask);
                    adapter.notifyDataSetChanged();
                    taskInput.setText("");
                }
            }
        });
    }

    @Override
    public void onDeleteTask(int position) {
        Task task = tasks.get(position);
        database.deleteTask(task);
        tasks.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
    }
}