package com.rpindv.backend_task.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDTO {
    private String email;
    private String password;
}
