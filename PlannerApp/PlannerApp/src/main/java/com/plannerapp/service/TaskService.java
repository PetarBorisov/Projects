package com.plannerapp.service;

import com.plannerapp.model.dto.HomeTaskDTO;
import com.plannerapp.model.dto.TaskAddDTO;

public interface TaskService {

    void add(TaskAddDTO taskAddDTO);

    void remove(Long id);

    void assign(Long id, String username);

    HomeTaskDTO getHomeViewDate(String username);
}
