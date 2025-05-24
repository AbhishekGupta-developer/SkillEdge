package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.CourseRequestDTO;
import com.myorganisation.SkillEdge.dto.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO);
    CourseResponseDTO getCourse(Long id);
    List<CourseResponseDTO> getAllCourses();
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO);
    String removeCourse(Long id);
}
