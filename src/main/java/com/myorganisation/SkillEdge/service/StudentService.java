package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.request.StudentRequestDto;
import com.myorganisation.SkillEdge.dto.response.StudentResponseDto;
import com.myorganisation.SkillEdge.model.enums.Gender;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    StudentResponseDto addStudent(StudentRequestDto studentRequestDTO);
    StudentResponseDto getStudent(Long id);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDTO);
    String removeStudent(Long id);

    List<StudentResponseDto> getStudentsByName(String name);
    List<StudentResponseDto> getStudentsByNameLike(String name);
    List<StudentResponseDto> getStudentsByNameLikeAndGender(String name, Gender gender);

    List<StudentResponseDto> getStudentsByAddressLike(String address);
    List<StudentResponseDto> getStudentsByPhoneLike(String phone);

    Page<StudentResponseDto> getStudentPage(Integer pageNumber, Integer pageSize, String sortBy, String orderIn);
}
