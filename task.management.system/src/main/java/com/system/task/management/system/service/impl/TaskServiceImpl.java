package com.system.task.management.system.service.impl;

import com.system.task.management.system.dto.TaskDTO;
import com.system.task.management.system.dto.requestdto.RequestTaskSaveDTO;
import com.system.task.management.system.dto.requestdto.RequestTaskUpdateDTO;
import com.system.task.management.system.entity.Task;
import com.system.task.management.system.exception.EntryNotFoundException;
import com.system.task.management.system.repo.TaskRepo;
import com.system.task.management.system.service.TaskService;
import com.system.task.management.system.util.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepo taskRepo, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
    }

    @Override
    public boolean createTask(RequestTaskSaveDTO dto) {
        if (dto.getTaskName() == null || dto.getTaskDescription() == null) {
            return false;
        }

        String userLastId = taskRepo.findLastId("TMS-T-", 7);
        String taskId = "TMS-T-1";
        if (null != userLastId) {
            int i = (Integer.parseInt(userLastId.split("TMS-T-")[1])) + 1;
            taskId = "TMS-T-" + i;
        }

        TaskDTO taskDTO = new TaskDTO(
                taskId,
                dto.getTaskName(),
                dto.getTaskDescription(),
                true
        );
        if (!taskRepo.existsById(taskDTO.getTaskId())){
            taskRepo.save(taskMapper.toTask(taskDTO));
        }
        return true;
    }

    @Override
    public boolean updateTask(RequestTaskUpdateDTO dto, String taskId) {
        Optional<Task> selectedTask = taskRepo.findById(taskId);
        if (selectedTask.isEmpty()) {
            throw new EntryNotFoundException("Cannot Update... Task Not Found");
        }
        Task task = selectedTask.get();
        task.setTaskName(dto.getTaskName());
        task.setTaskDescription(dto.getTaskDescription());
        task.setActiveState(dto.isActiveState());
        taskRepo.save(task);
        return true;

    }

    @Override
    public boolean deleteTask(String taskId) {
        if (taskRepo.existsById(taskId)){
            taskRepo.deleteById(taskId);
            return true;
        }
        return false;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> allTasks = taskRepo.findAll();
        if (!allTasks.isEmpty()) {
            return taskMapper.tasksToResponseAllTaskDtos(allTasks);
        } else {
            throw new EntryNotFoundException("No Any Tasks Found");
        }
    }

    @Override
    public TaskDTO findTask(String taskId) {
        Task task = taskRepo.getTaskByTaskIdEquals(taskId);
        return taskMapper.toTaskDto(task);
    }

}
