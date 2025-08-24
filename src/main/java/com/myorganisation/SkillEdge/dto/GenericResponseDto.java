package com.myorganisation.SkillEdge.dto;

import lombok.Data;

@Data
public class GenericResponseDto {
    private boolean success;
    private String message;
    private Object details;
}
