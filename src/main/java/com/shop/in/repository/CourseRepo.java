package com.shop.in.repository;

import com.shop.in.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
    List<Course> findByOrderByNameDesc();
}
