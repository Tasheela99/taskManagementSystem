package com.system.task.management.system.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTaskUpdateDTO {
    private String taskName;
    private String taskDescription;
    private boolean activeState;
}
