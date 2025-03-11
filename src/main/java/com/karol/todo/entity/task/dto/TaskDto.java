package com.karol.todo.entity.task.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;

    private String taskName;

    private Date deadline;
}
