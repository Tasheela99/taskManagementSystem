package com.system.task.management.system.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private String propertyId;
    private boolean activeState;
    private String email;
    private String firstName;
    private String lastName;
    private String roleName;
}
