package com.system.task.management.system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "task_id", length = 80)
    private String taskId;

    @Column(name = "name", length = 255)
    private String taskName;

    @Column(name = "description", length = 255)
    private String taskDescription;

    @Column(name = "assigned_by", length = 255)
    private String taskAssignedBy;

    @Column(name = "assigned_for", length = 255)
    private String taskAssignedFor;

    @Column(name = "assigned_date",length = 255)
    private String taskAssignedDate;

    @Column(name = "deadline",length = 255)
    private String taskDeadLine;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

}
