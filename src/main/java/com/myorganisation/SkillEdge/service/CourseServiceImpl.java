package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.request.CourseRequestDto;
import com.myorganisation.SkillEdge.dto.response.CourseResponseDto;
import com.myorganisation.SkillEdge.model.Course;
import com.myorganisation.SkillEdge.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDTO) {
        Course course = new Course();
        course.setName(courseRequestDTO.getName());
        course.setDescription(courseRequestDTO.getDescription());
        course.setFee(courseRequestDTO.getFee());
        course.setDuration(courseRequestDTO.getDuration());

        course = courseRepository.save(course);

        CourseResponseDto courseResponseDTO = new CourseResponseDto();

        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setName(course.getName());
        courseResponseDTO.setDescription(course.getDescription());
        courseResponseDTO.setFee(course.getFee());
        courseResponseDTO.setDuration(course.getDuration());

        return courseResponseDTO;
    }

    @Override
    public CourseResponseDto getCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);

        CourseResponseDto courseResponseDTO = new CourseResponseDto();

        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setName(course.getName());
        courseResponseDTO.setDescription(course.getDescription());
        courseResponseDTO.setFee(course.getFee());
        courseResponseDTO.setDuration(course.getDuration());

        return courseResponseDTO;
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();

        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();

        for(Course course: courseList) {
            CourseResponseDto courseResponseDTO = new CourseResponseDto();

            courseResponseDTO.setId(course.getId());
            courseResponseDTO.setName(course.getName());
            courseResponseDTO.setDescription(course.getDescription());
            courseResponseDTO.setFee(course.getFee());
            courseResponseDTO.setDuration(course.getDuration());

            courseResponseDtoList.add(courseResponseDTO);
        }

        return courseResponseDtoList;
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDTO) {
        //Get data from table
        Course course = courseRepository.findById(id).orElse(null);

        //Update existing data with new data
        course.setName(courseRequestDTO.getName());
        course.setDescription(courseRequestDTO.getDescription());
        course.setFee(courseRequestDTO.getFee());
        course.setDuration(courseRequestDTO.getDuration());

        //Save new data into table
        course = courseRepository.save(course);

        //Convert Course into CourseResponseDTO
        CourseResponseDto courseResponseDTO = new CourseResponseDto();

        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setName(course.getName());
        courseResponseDTO.setDescription(course.getDescription());
        courseResponseDTO.setFee(course.getFee());
        courseResponseDTO.setDuration(course.getDuration());

        return courseResponseDTO;
    }

    @Override
    public String removeCourse(Long id) {
        String name = courseRepository.findById(id).orElse(null).getName();
        courseRepository.deleteById(id);

        return  "Course name: " + name + "(" + id + ") has been removed successfully";
    }
}
