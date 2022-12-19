package com.ada.modulo5.school_api.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.ada.modulo5.school_api.model.Course;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CourseService {

    public List<Course> listCourses() {
        log.info("All Courses retrieved");
        return Arrays.asList(new Course(), new Course());
    }

    public Course getCourse(int courseId) {
        log.info("Course ID {} retrieved", courseId);
        return new Course();
    }

    public boolean insertCourse(Course course) {
        log.info("Course ID {} inserted", course.getId());
        return true;
    }

    public boolean updateCourse(Course course) {
        log.info("Course ID {} updated", course.getId());
        return true;
    }

    public boolean deleteCourse(Course course) {
        log.info("Course ID {} deleted", course.getId());
        return true;
    }

}