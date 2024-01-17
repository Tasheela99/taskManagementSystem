package com.system.task.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO implements SuperDTO {
    private String propertyId;

    private boolean activeState;


    private String email;

    private String firstName;

    private String lastName;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    private String password;

    private Date registerDate;

    private String username;

    private UserRoleDTO userRole;

    public UserDTO(String propertyId, String email, String firstName, String lastName) {
        this.propertyId = propertyId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
