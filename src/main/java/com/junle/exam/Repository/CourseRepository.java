package com.junle.exam.Repository;

import com.junle.exam.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course getByCourseName(String courseName);
}
