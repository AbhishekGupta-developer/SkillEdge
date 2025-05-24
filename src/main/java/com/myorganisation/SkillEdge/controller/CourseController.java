package com.myorganisation.SkillEdge.controller;

import com.myorganisation.SkillEdge.dto.CourseRequestDTO;
import com.myorganisation.SkillEdge.dto.CourseResponseDTO;
import com.myorganisation.SkillEdge.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> addCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        return new ResponseEntity<>(courseService.addCourse(courseRequestDTO), HttpStatusCode.valueOf(201));
    }
}
