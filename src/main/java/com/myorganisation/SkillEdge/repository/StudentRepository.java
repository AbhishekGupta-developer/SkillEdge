package com.myorganisation.SkillEdge.repository;

import com.myorganisation.SkillEdge.enums.Gender;
import com.myorganisation.SkillEdge.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Custom finder method
    List<Student> findByName(String name);
    List<Student> findByNameContaining(String name);
    List<Student> findByNameContainingAndGender(String name, Gender gender);

    //JPQL query
    @Query("SELECT s FROM Student s WHERE s.address LIKE %:address%")
    List<Student> searchStudentsByAddress(@Param(value = "address") String address);

    //SQL Query
    @Query(value = "SELECT * FROM student s WHERE s.phone LIKE %:phone%", nativeQuery = true)
    List<Student> searchStudentsByPhone(@Param("phone") String phone);

}
