package com.system.task.management.system.util.mapper;

import com.system.task.management.system.dto.TaskDTO;
import com.system.task.management.system.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskDTO taskDTO);
    List<TaskDTO> tasksToResponseAllTaskDtos(List<Task> allTasks);

    TaskDTO toTaskDto(Task task);
}
