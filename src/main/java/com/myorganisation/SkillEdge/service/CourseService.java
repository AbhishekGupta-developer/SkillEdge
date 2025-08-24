package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.request.CourseRequestDto;
import com.myorganisation.SkillEdge.dto.response.CourseResponseDto;

import java.util.List;

public interface CourseService {
    CourseResponseDto addCourse(CourseRequestDto courseRequestDTO);
    CourseResponseDto getCourse(Long id);
    List<CourseResponseDto> getAllCourses();
    CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDTO);
    String removeCourse(Long id);
}
