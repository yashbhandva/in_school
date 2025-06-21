package com.shop.in.servicImpl;

import com.shop.in.model.Course;
import com.shop.in.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl {
    @Autowired
    private CourseRepo courseRepo;

    public List<Course> findAllCourse(){
        //return courseRepo.findByOrderByNameDesc(); static shorting
        return courseRepo.findAll(Sort.by("name").descending());
    }

    public Course saveCourse(Course course){
        return courseRepo.save(course);
    }

    public Optional<Course> findById(int id){
        return courseRepo.findById(id);
    }
}
