package com.myorganisation.SkillEdge.dto;

import com.myorganisation.SkillEdge.model.Account;
import com.myorganisation.SkillEdge.model.enums.Gender;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentResponseDto {
    private Long id;
    private String name;
    private Gender gender;
    private String address;
    private String email;
    private String phone;
    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;
    private CourseResponseDto course;
    private Account account;
}
