package com.myorganisation.SkillEdge.dto;

import lombok.Data;

@Data
public class CourseRequestDTO {
    private String name;
    private String description;
    private Double fee;
    private Double duration;
}
