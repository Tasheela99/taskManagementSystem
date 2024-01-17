package com.system.task.management.system.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseUserDataDTO {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String roleId;
    private String roleName;

}
