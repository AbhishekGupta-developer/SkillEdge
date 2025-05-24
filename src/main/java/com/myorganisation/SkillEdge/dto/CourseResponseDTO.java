package com.myorganisation.SkillEdge.dto;

import lombok.Data;

@Data
public class CourseResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double fee;
    private Double duration;
}
