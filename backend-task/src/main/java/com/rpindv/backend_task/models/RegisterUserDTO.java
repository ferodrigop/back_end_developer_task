package com.rpindv.backend_task.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserDTO {
    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private String password;
}
