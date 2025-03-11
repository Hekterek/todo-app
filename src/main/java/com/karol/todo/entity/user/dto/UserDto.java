package com.karol.todo.entity.user.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class UserDto {
    private String email;

    private String username;

    private String role;
}
