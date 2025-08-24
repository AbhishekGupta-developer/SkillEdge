package com.myorganisation.SkillEdge.dto.response;

import lombok.Data;

@Data
public class GenericResponseDto {
    private boolean success;
    private String message;
    private Object details;
}
