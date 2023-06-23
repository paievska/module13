package org.example.task3;

public class CompletedTaskItems {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
