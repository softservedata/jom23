package com.softserve.itacademy.model;

import java.time.LocalDateTime;
import java.util.List;

public class ToDo {

    private String title;

    private LocalDateTime createdAt;

    private User owner;

    private List<Task> tasks;

    public ToDo(String title, User owner, List<Task> tasks) {
        this.title = title;
        this.owner = owner;
        this.tasks = tasks;
        this.createdAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDo toDo = (ToDo) o;

        if (getTitle() != null ? !getTitle().equals(toDo.getTitle()) : toDo.getTitle() != null) return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(toDo.getCreatedAt()) : toDo.getCreatedAt() != null)
            return false;
        if (getOwner() != null ? !getOwner().equals(toDo.getOwner()) : toDo.getOwner() != null) return false;
        return getTasks() != null ? getTasks().equals(toDo.getTasks()) : toDo.getTasks() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + (getTasks() != null ? getTasks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", owner=" + owner +
                ", tasks=" + tasks +
                '}';
    }
    // Constructor(s), getters, setters, hashCode, equals, etc.


}
