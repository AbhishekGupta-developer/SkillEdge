package com.myorganisation.SkillEdge.service;

import com.myorganisation.SkillEdge.dto.response.CourseResponseDto;
import com.myorganisation.SkillEdge.dto.request.StudentRequestDto;
import com.myorganisation.SkillEdge.dto.response.StudentResponseDto;
import com.myorganisation.SkillEdge.exception.StudentNotFoundException;
import com.myorganisation.SkillEdge.model.Account;
import com.myorganisation.SkillEdge.model.Course;
import com.myorganisation.SkillEdge.model.Student;
import com.myorganisation.SkillEdge.model.enums.Gender;
import com.myorganisation.SkillEdge.repository.CourseRepository;
import com.myorganisation.SkillEdge.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public StudentResponseDto addStudent(StudentRequestDto studentRequestDTO) {
        Student student = copyStudentRequestDTOToStudent(studentRequestDTO, new Student());
        Account account = new Account();
        account.setStudent(student);
        student.setAccount(account);
        student = studentRepository.save(student);
        return mapStudentToStudentResponseDTO(student);
    }

    @Override
    public StudentResponseDto getStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student id: " + id + " doesn't exist"));
        return mapStudentToStudentResponseDTO(student);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDTO(student));
        }
        return studentResponseDtoList;
    }

    @Override
    @Transactional
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student id: " + id + " doesn't exist"));
        copyStudentRequestDTOToStudent(studentRequestDTO, student);
        studentRepository.save(student);
        return mapStudentToStudentResponseDTO(student);
    }

    @Override
    public String removeStudent(Long id) {
        String name = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student id: " + id + " doesn't exist")).getName();
        studentRepository.deleteById(id);
        return "Student name: " + name + "(" + id +") has been removed successfully.";
    }

    @Override
    public List<StudentResponseDto> getStudentsByName(String name) {
        List<Student> studentList = studentRepository.findByName(name);
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDTO(student));
        }
        return studentResponseDtoList;
    }

    @Override
    public List<StudentResponseDto> getStudentsByNameLike(String name) {
        List<Student> studentList = studentRepository.findByNameContaining(name);
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDTO(student));
        }
        return studentResponseDtoList;
    }

    @Override
    public List<StudentResponseDto> getStudentsByNameLikeAndGender(String name, Gender gender) {
        List<Student> studentList = studentRepository.findByNameContainingAndGender(name, gender);
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDTO(student));
        }
        return studentResponseDtoList;
    }

    @Override
    public List<StudentResponseDto> getStudentsByAddressLike(String address) {
        List<Student> studentList = studentRepository.searchStudentsByAddress(address);
        List<StudentResponseDto> studentResponseDtoList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDTO(student));
        }

        return studentResponseDtoList;
    }

    @Override
    public List<StudentResponseDto> getStudentsByPhoneLike(String phone) {
        List<Student> studentList = studentRepository.searchStudentsByPhone(phone);
        List<StudentResponseDto> studentResponseDtoList = new LinkedList<>();

        for(Student student : studentList) {
            studentResponseDtoList.add(mapStudentToStudentResponseDTO(student));
        }

        return studentResponseDtoList;
    }

    @Override
    public Page<StudentResponseDto> getStudentPage(Integer pageNumber, Integer pageSize, String sortBy, String orderIn) {
        Sort sort = (orderIn.equalsIgnoreCase("desc")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        Page<StudentResponseDto> studentResponseDTOPage = studentPage.map(this::mapStudentToStudentResponseDTO);

        return studentResponseDTOPage;
    }

    //Helper methods

    //Copy StudentRequestDTO to Student
    private Student copyStudentRequestDTOToStudent(StudentRequestDto studentRequestDTO, Student student) {
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
    private StudentResponseDto mapStudentToStudentResponseDTO(Student student) {
        StudentResponseDto studentResponseDTO = new StudentResponseDto();

        Course course = student.getCourse();
        CourseResponseDto courseResponseDTO = new CourseResponseDto();
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
