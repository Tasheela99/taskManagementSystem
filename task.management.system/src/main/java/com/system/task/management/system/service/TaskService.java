package com.system.task.management.system.service;

import com.system.task.management.system.dto.TaskDTO;
import com.system.task.management.system.dto.requestdto.RequestTaskSaveDTO;
import com.system.task.management.system.dto.requestdto.RequestTaskUpdateDTO;

import java.util.List;

public interface TaskService {
    boolean createTask(RequestTaskSaveDTO dto);

    boolean updateTask(RequestTaskUpdateDTO dto, String taskId);

    boolean deleteTask(String taskId);

    List<TaskDTO> getAllTasks();

    TaskDTO findTask(String taskId);
}
