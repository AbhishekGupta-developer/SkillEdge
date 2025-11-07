package com.myorganisation.SkillEdge.controller;

import com.myorganisation.SkillEdge.dto.request.StudentRequestDto;
import com.myorganisation.SkillEdge.dto.response.StudentResponseDto;
import com.myorganisation.SkillEdge.enums.Gender;
import com.myorganisation.SkillEdge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentRequestDto studentRequestDTO) {
        return new ResponseEntity<>(studentService.addStudent(studentRequestDTO), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getALlStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto studentRequestDTO) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentRequestDTO), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<String> removeStudent(@RequestParam Long id) {
        return new ResponseEntity<>(studentService.removeStudent(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByName(@PathVariable String name) {
        return new ResponseEntity<>(studentService.getStudentsByName(name), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find/like/name/{name}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByNameLike(@PathVariable String name) {
        return new ResponseEntity<>(studentService.getStudentsByNameLike(name), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find/like/address/{address}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByAddressLike(@PathVariable String address) {
        return new ResponseEntity<>(studentService.getStudentsByAddressLike(address), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find/like/phone/{phone}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByPhoneLike(@PathVariable String phone) {
        return new ResponseEntity<>(studentService.getStudentsByPhoneLike(phone), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/find")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByNameLikeAndGender(@RequestParam String name, @RequestParam Gender gender) {
        return new ResponseEntity<>(studentService.getStudentsByNameLikeAndGender(name, gender), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<StudentResponseDto>> getStudentPage(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String orderIn
    ) {
        return new ResponseEntity<>(studentService.getStudentPage(pageNumber, pageSize, sortBy, orderIn), HttpStatus.OK);
    }
}
