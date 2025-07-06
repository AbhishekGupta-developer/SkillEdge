package com.myorganisation.SkillEdge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double total = 0D;
    private Double submitted = 0D;
    private Double balance = 0D;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private Student student;
}
