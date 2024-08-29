package com.example.to_do_list;

public class Task {
    private long id;
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public Task(long id, String title, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void toggleCompletion() {
        isCompleted = !isCompleted;
    }
}