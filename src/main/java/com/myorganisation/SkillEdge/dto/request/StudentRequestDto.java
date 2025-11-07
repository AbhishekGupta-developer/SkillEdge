package com.myorganisation.SkillEdge.dto.request;

import com.myorganisation.SkillEdge.enums.Gender;
import lombok.Data;

@Data
public class StudentRequestDto {
    private String name;
    private Gender gender;
    private String address;
    private String email;
    private String phone;
    private Long courseId;
}
