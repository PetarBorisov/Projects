package com.plannerapp.service.impl;

import com.plannerapp.model.dto.HomeTaskDTO;
import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.model.dto.TaskDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(TaskAddDTO taskAddDTO) {

        Priority priority = priorityRepository.findByName(taskAddDTO.getPriority());

        if (priority != null) {
            Task task = new Task();
            task.setDescription(taskAddDTO.getDescription());
            task.setDueDate(LocalDate.parse(taskAddDTO.getDueDate()))  ;
            task.setPriority(priority);

            taskRepository.save(task);
        }
    }

    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void assign(Long id, String username) {
     Optional<Task> optionalTask = taskRepository.findById(id);



     if (optionalTask.isPresent()){
         Task task = optionalTask.get();

         if (username == null) {
             task.setAssignee(null);
         }else {
             User user = userRepository.findByUsername(username);
             task.setAssignee(user);
         }

         taskRepository.save(task);
     }

    }

    @Override
    public HomeTaskDTO getHomeViewDate(String username) {
        User user = userRepository.findByUsername(username);


        List<TaskDTO> assignedTasks = taskRepository.findByAssignee(user)
                .stream()
                .map(task -> TaskDTO.createFromTask(task))
                .toList();

        List<TaskDTO> available = taskRepository.getAllAvailable()
                .stream()
                .map(task -> TaskDTO.createFromTask(task))
                .toList();;

        return new HomeTaskDTO(assignedTasks, available);

    }
}
