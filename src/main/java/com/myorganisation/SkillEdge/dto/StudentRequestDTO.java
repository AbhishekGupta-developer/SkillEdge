package com.myorganisation.SkillEdge.dto;

import com.myorganisation.SkillEdge.model.enums.Gender;
import lombok.Data;

@Data
public class StudentRequestDTO {
    private String name;
    private Gender gender;
    private String address;
    private String email;
    private Long phone;
}
