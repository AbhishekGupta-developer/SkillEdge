package com.myorganisation.SkillEdge.dto.response;

import com.myorganisation.SkillEdge.model.enums.UserRole;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private UserRole role;
}
