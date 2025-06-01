package com.myorganisation.SkillEdge.controller;

import com.myorganisation.SkillEdge.dto.StudentRequestDTO;
import com.myorganisation.SkillEdge.dto.StudentResponseDTO;
import com.myorganisation.SkillEdge.model.enums.Gender;
import com.myorganisation.SkillEdge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return new ResponseEntity<>(studentService.addStudent(studentRequestDTO), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getALlStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentRequestDTO) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentRequestDTO), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<String> removeStudent(@RequestParam Long id) {
        return new ResponseEntity<>(studentService.removeStudent(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByName(@PathVariable String name) {
        return new ResponseEntity<>(studentService.getStudentsByName(name), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find/like/name/{name}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByNameLike(@PathVariable String name) {
        return new ResponseEntity<>(studentService.getStudentsByNameLike(name), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByNameLikeAndGender(@RequestParam String name, @RequestParam Gender gender) {
        return new ResponseEntity<>(studentService.getStudentsByNameLikeAndGender(name, gender), HttpStatusCode.valueOf(200));
    }
}
