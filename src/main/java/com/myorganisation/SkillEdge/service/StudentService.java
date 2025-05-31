package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.StudentRequestDTO;
import com.myorganisation.SkillEdge.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO getStudent(Long id);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);
    String removeStudent(Long id);
}
