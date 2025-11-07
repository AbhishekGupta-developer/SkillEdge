package com.myorganisation.SkillEdge.dto.request;

import com.myorganisation.SkillEdge.enums.UserRole;
import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private UserRole role;
    private String password;
}
