package com.ada.modulo5.school_api.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.ada.modulo5.school_api.model.Student;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class StudentService {

    public List<Student> listStudents() {
        log.info("All Students retrieved");
        return Arrays.asList(new Student(), new Student());
    }

    public Student getStudent(int studentId) {
        log.info("Student ID {} retrieved", studentId);
        return new Student();
    }

    public boolean insertStudent(Student student) {
        log.info("Student ID {} inserted", student.getId());
        return true;
    }

    public boolean updateStudent(Student student) {
        log.info("Student ID {} updated", student.getId());
        return true;
    }

    public boolean deleteStudent(Student student) {
        log.info("Student ID {} deleted", student.getId());
        return true;
    }

}