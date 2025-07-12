package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.CourseResponseDTO;
import com.myorganisation.SkillEdge.dto.StudentRequestDTO;
import com.myorganisation.SkillEdge.dto.StudentResponseDTO;
import com.myorganisation.SkillEdge.model.Account;
import com.myorganisation.SkillEdge.model.Course;
import com.myorganisation.SkillEdge.model.Student;
import com.myorganisation.SkillEdge.model.enums.Gender;
import com.myorganisation.SkillEdge.repository.CourseRepository;
import com.myorganisation.SkillEdge.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        Student student = copyStudentRequestDTOToStudent(studentRequestDTO, new Student());
        Account account = new Account();
        account.setStudent(student);
        student.setAccount(account);
        student = studentRepository.save(student);
        return mapStudentToStudentResponseDTO(student);
    }

    @Override
    public StudentResponseDTO getStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return mapStudentToStudentResponseDTO(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDTOList.add(mapStudentToStudentResponseDTO(student));
        }
        return studentResponseDTOList;
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id).orElse(null);
        copyStudentRequestDTOToStudent(studentRequestDTO, student);
        studentRepository.save(student);
        return mapStudentToStudentResponseDTO(student);
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
            studentResponseDTOList.add(mapStudentToStudentResponseDTO(student));
        }
        return studentResponseDTOList;
    }

    @Override
    public List<StudentResponseDTO> getStudentsByNameLike(String name) {
        List<Student> studentList = studentRepository.findByNameContaining(name);
        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDTOList.add(mapStudentToStudentResponseDTO(student));
        }
        return studentResponseDTOList;
    }

    @Override
    public List<StudentResponseDTO> getStudentsByNameLikeAndGender(String name, Gender gender) {
        List<Student> studentList = studentRepository.findByNameContainingAndGender(name, gender);
        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDTOList.add(mapStudentToStudentResponseDTO(student));
        }
        return studentResponseDTOList;
    }

    @Override
    public List<StudentResponseDTO> getStudentsByAddressLike(String address) {
        List<Student> studentList = studentRepository.searchStudentsByAddress(address);
        List<StudentResponseDTO> studentResponseDTOList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDTOList.add(mapStudentToStudentResponseDTO(student));
        }

        return studentResponseDTOList;
    }

    @Override
    public List<StudentResponseDTO> getStudentsByPhoneLike(String phone) {
        List<Student> studentList = studentRepository.searchStudentsByPhone(phone);
        List<StudentResponseDTO> studentResponseDTOList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDTOList.add(mapStudentToStudentResponseDTO(student));
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
        if(studentRequestDTO.getCourseId() != null) {
            student.setCourse(courseRepository.findById(studentRequestDTO.getCourseId()).orElse(null));
        }

        return student;
    }

    //Convert Student to StudentResponseDTO
    private StudentResponseDTO mapStudentToStudentResponseDTO(Student student) {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        Course course = student.getCourse();
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setName(course.getName());
        courseResponseDTO.setDescription(course.getDescription());
        courseResponseDTO.setFee(course.getFee());
        courseResponseDTO.setDuration(course.getDuration());

        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setGender(student.getGender());
        studentResponseDTO.setAddress(student.getAddress());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setPhone(student.getPhone());
        studentResponseDTO.setRegisteredAt(student.getRegisteredAt());
        studentResponseDTO.setUpdatedAt(student.getUpdatedAt());
        studentResponseDTO.setCourse(courseResponseDTO);
        studentResponseDTO.setAccount(student.getAccount());

        return studentResponseDTO;
    }

}
