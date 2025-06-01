package com.myorganisation.SkillEdge.repository;

import com.myorganisation.SkillEdge.model.Student;
import com.myorganisation.SkillEdge.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Custom finder method
    List<Student> findByName(String name);
    List<Student> findByNameContaining(String name);
    List<Student> findByNameContainingAndGender(String name, Gender gender);
}
