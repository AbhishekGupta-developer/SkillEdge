package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.CourseRequestDTO;
import com.myorganisation.SkillEdge.dto.CourseResponseDTO;
import com.myorganisation.SkillEdge.model.Course;
import com.myorganisation.SkillEdge.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO) {
        Course course = new Course();
        course.setName(courseRequestDTO.getName());
        course.setDescription(courseRequestDTO.getDescription());
        course.setFee(courseRequestDTO.getFee());
        course.setDuration(courseRequestDTO.getDuration());

        course = courseRepository.save(course);

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setName(course.getName());
        courseResponseDTO.setDescription(course.getDescription());
        courseResponseDTO.setFee(course.getFee());
        courseResponseDTO.setDuration(course.getDuration());

        return courseResponseDTO;
    }

    @Override
    public CourseResponseDTO getCourse(Long id) {
        return null;
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        return List.of();
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {
        return null;
    }

    @Override
    public String removeCourse(Long id) {
        return "";
    }
}
