package com.system.task.management.system.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserSaveDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
