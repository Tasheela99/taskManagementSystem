package com.system.task.management.system.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTaskUpdateDTO {
    private String taskName;
    private String taskDescription;
    private String taskAssignedBy;
    private String taskAssignedFor;
    private String taskAssignedDate;
    private String taskDeadLine;
    private boolean activeState;
}
