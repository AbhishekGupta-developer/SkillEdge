package com.myorganisation.SkillEdge.dto.request;

import lombok.Data;

@Data
public class CourseRequestDto {
    private String name;
    private String description;
    private Double fee;
    private Double duration;
}
