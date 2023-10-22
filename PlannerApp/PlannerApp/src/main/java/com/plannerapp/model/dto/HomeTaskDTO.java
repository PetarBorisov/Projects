package com.plannerapp.model.dto;

import com.plannerapp.model.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public class HomeTaskDTO {

    private List<TaskDTO> assignedTasks;
    private List<TaskDTO> availableTasks;

    private int availableSize;

    public HomeTaskDTO() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public HomeTaskDTO(List<TaskDTO> assignedTasks, List<TaskDTO> availableTasks) {
        this.assignedTasks = assignedTasks;
        this.availableTasks = availableTasks;
        this.availableSize = availableTasks.size();
    }

    public List<TaskDTO> getAssignedTasks() {
        return assignedTasks;
    }

    public List<TaskDTO> getAvailableTasks() {
        return availableTasks;
    }

    public int getAvailableSize() {
        return availableSize;
    }

}