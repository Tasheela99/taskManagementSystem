package com.system.task.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String taskId;
    private String taskName;
    private String taskDescription;
    private String taskAssignedBy;
    private String taskAssignedFor;
    private String taskAssignedDate;
    private String taskDeadLine;
    private boolean activeState;
}
