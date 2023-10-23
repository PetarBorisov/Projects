package com.plannerapp.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity{


    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String description;

    @Future
    @Column(nullable = false)
    private LocalDate dueDate;

    @ManyToOne
    private Priority priority;

    @ManyToOne
    private User assignee;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }
}
