package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.StudentRequestDTO;
import com.myorganisation.SkillEdge.dto.StudentResponseDTO;
import com.myorganisation.SkillEdge.model.enums.Gender;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO getStudent(Long id);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);
    String removeStudent(Long id);

    List<StudentResponseDTO> getStudentsByName(String name);
    List<StudentResponseDTO> getStudentsByNameLike(String name);
    List<StudentResponseDTO> getStudentsByNameLikeAndGender(String name, Gender gender);

    List<StudentResponseDTO> getStudentsByAddressLike(String address);
    List<StudentResponseDTO> getStudentsByPhoneLike(String phone);

    Page<StudentResponseDTO> getStudentPage(Integer pageNumber, Integer pageSize, String sortBy, String orderIn);
}
