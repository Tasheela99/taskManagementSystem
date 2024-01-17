package com.system.task.management.system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(length = 80, name = "property_id")
    private String propertyId;

    @Column(name = "active_state",  columnDefinition = "TINYINT")
    private boolean activeState;

    @Column(length = 250, name = "email")
    private String email;

    @Column(length = 45, name = "first_name")
    private String firstName;

    @Column(length = 45, name = "last_name")
    private String lastName;
    @Column(name = "is_account_non_expired",  columnDefinition = "TINYINT")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked",  columnDefinition = "TINYINT")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired",  columnDefinition = "TINYINT")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled",  columnDefinition = "TINYINT")
    private boolean isEnabled;

    @Column(length = 750, name = "password")
    private String password;

    @Column( name = "register_date")
    private Date registerDate;

    @Column(length = 250, name = "user_name", unique = true)
    private String username;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private UserRole userRole;



}
