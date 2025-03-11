package com.karol.todo.entity.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class LoginRequestDTO {

    private String email;
    private String password;
}
