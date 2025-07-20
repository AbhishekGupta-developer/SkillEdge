package com.myorganisation.SkillEdge.controller;

import com.myorganisation.SkillEdge.dto.CourseRequestDTO;
import com.myorganisation.SkillEdge.dto.CourseResponseDTO;
import com.myorganisation.SkillEdge.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> addCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        return new ResponseEntity<>(courseService.addCourse(courseRequestDTO), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO courseRequestDTO) {
        return new ResponseEntity<>(courseService.updateCourse(id, courseRequestDTO), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<String> removeCourse(@RequestParam Long id) {
        return new ResponseEntity<>(courseService.removeCourse(id), HttpStatusCode.valueOf(200));
    }
}
