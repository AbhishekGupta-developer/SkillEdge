package com.myorganisation.SkillEdge.repository;

import com.myorganisation.SkillEdge.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
