package com.softserve.itacademy.model;

import java.time.LocalDateTime;
import java.util.List;

public class ToDo {

    private int id;

    private String title;

    private User owner;

    private List<Task> tasks;

    // Constructor(s), getters, setters, hashCode, equals, etc.

    public ToDo(int id, String title, User owner, List<Task> tasks) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

        if (getId() != toDo.getId()) return false;
        if (getTitle() != null ? !getTitle().equals(toDo.getTitle()) : toDo.getTitle() != null) return false;
        if (getOwner() != null ? !getOwner().equals(toDo.getOwner()) : toDo.getOwner() != null) return false;
        return getTasks() != null ? getTasks().equals(toDo.getTasks()) : toDo.getTasks() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + (getTasks() != null ? getTasks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", owner=" + owner +
                ", tasks=" + tasks +
                '}';
    }
}
