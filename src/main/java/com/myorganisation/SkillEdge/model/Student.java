package com.myorganisation.SkillEdge.model;

import com.myorganisation.SkillEdge.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Gender gender;
    private String address;
    private String email;
    private Long phone;
}
