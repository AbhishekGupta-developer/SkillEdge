package com.myorganisation.SkillEdge.dto.response;

import com.myorganisation.SkillEdge.enums.Gender;
import com.myorganisation.SkillEdge.model.Account;
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
