package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.StudentRequestDTO;
import com.myorganisation.SkillEdge.dto.StudentResponseDTO;
import com.myorganisation.SkillEdge.model.Student;
import com.myorganisation.SkillEdge.model.enums.Gender;
import com.myorganisation.SkillEdge.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        Student student = copyStudentRequestDTOToStudent(studentRequestDTO, new Student());
        student = studentRepository.save(student);
        return convertStudentToStudentResponseDTO(student);
    }

    @Override
    public StudentResponseDTO getStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return convertStudentToStudentResponseDTO(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDTOList.add(convertStudentToStudentResponseDTO(student));
        }
        return studentResponseDTOList;
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id).orElse(null);
        copyStudentRequestDTOToStudent(studentRequestDTO, student);
        studentRepository.save(student);
        return convertStudentToStudentResponseDTO(student);
    }

    @Override
    public String removeStudent(Long id) {
        String name = studentRepository.findById(id).orElse(null).getName();
        studentRepository.deleteById(id);
        return "Student name: " + name + "(" + id +") has been removed successfully.";
    }

    @Override
    public List<StudentResponseDTO> getStudentsByName(String name) {
        List<Student> studentList = studentRepository.findByName(name);
        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDTOList.add(convertStudentToStudentResponseDTO(student));
        }
        return studentResponseDTOList;
    }

    @Override
    public List<StudentResponseDTO> getStudentsByNameLike(String name) {
        List<Student> studentList = studentRepository.findByNameContaining(name);
        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDTOList.add(convertStudentToStudentResponseDTO(student));
        }
        return studentResponseDTOList;
    }

    @Override
    public List<StudentResponseDTO> getStudentsByNameLikeAndGender(String name, Gender gender) {
        List<Student> studentList = studentRepository.findByNameContainingAndGender(name, gender);
        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDTOList.add(convertStudentToStudentResponseDTO(student));
        }
        return studentResponseDTOList;
    }

    //Helper methods

    //Copy StudentRequestDTO to Student
    private Student copyStudentRequestDTOToStudent(StudentRequestDTO studentRequestDTO, Student student) {
        student.setName(studentRequestDTO.getName());
        student.setGender(studentRequestDTO.getGender());
        student.setAddress(studentRequestDTO.getAddress());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPhone(studentRequestDTO.getPhone());

        return student;
    }

    //Convert Student to StudentResponseDTO
    private StudentResponseDTO convertStudentToStudentResponseDTO(Student student) {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setGender(student.getGender());
        studentResponseDTO.setAddress(student.getAddress());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setPhone(student.getPhone());

        return studentResponseDTO;
    }

}
