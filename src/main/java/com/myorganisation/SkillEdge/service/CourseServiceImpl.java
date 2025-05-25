package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.CourseRequestDTO;
import com.myorganisation.SkillEdge.dto.CourseResponseDTO;
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
        Course course = courseRepository.findById(id).orElse(null);

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setName(course.getName());
        courseResponseDTO.setDescription(course.getDescription());
        courseResponseDTO.setFee(course.getFee());
        courseResponseDTO.setDuration(course.getDuration());

        return courseResponseDTO;
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();

        List<CourseResponseDTO> courseResponseDTOList = new ArrayList<>();

        for(Course course: courseList) {
            CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

            courseResponseDTO.setId(course.getId());
            courseResponseDTO.setName(course.getName());
            courseResponseDTO.setDescription(course.getDescription());
            courseResponseDTO.setFee(course.getFee());
            courseResponseDTO.setDuration(course.getDuration());

            courseResponseDTOList.add(courseResponseDTO);
        }

        return courseResponseDTOList;
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {
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
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

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
