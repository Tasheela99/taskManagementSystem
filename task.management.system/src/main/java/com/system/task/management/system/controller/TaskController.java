package com.system.task.management.system.controller;

import com.system.task.management.system.dto.TaskDTO;
import com.system.task.management.system.dto.requestdto.RequestTaskSaveDTO;
import com.system.task.management.system.dto.requestdto.RequestTaskUpdateDTO;
import com.system.task.management.system.service.TaskService;
import com.system.task.management.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/task")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping(path = "/create")
    public ResponseEntity<StandardResponse> createTask(
            @RequestBody RequestTaskSaveDTO dto
    ) {
        boolean isCreated = taskService.createTask(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Created",
                        isCreated
                ), HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/update", params = {"taskId"})
    public ResponseEntity<StandardResponse> updateTask(
            @RequestBody RequestTaskUpdateDTO dto,
            @RequestParam(value = "taskId") String taskId
    ) {
        boolean isUpdated = taskService.updateTask(dto, taskId);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Updated",
                        isUpdated
                ), HttpStatus.OK
        );

    }

    @DeleteMapping(path = "/delete", params = {"taskId"})
    public ResponseEntity<StandardResponse> deleteTask(
            @RequestParam(value = "taskId") String taskId
    ) {
        boolean isDeleted = taskService.deleteTask(taskId);
        return new ResponseEntity<>(
                new StandardResponse(
                        204,
                        "Updated",
                        isDeleted
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/find", params = {"taskId"})
    public ResponseEntity<StandardResponse> findTask(
            @RequestParam(value = "taskId") String taskId
    ) {
        TaskDTO taskDto = taskService.findTask(taskId);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Task " + taskId,
                        taskDto
                ),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get")
    public ResponseEntity<StandardResponse> getAllTasks() {
        List<TaskDTO> tasksList = taskService.getAllTasks();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Tasks",
                        tasksList
                ),
                HttpStatus.OK
        );
    }

}
