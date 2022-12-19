package com.ada.modulo5.school_api.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.ada.modulo5.school_api.model.Teacher;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class TeacherService {

    public List<Teacher> listTeachers() {
        log.info("All Teachers retrieved");
        return Arrays.asList(new Teacher(), new Teacher());
    }

    public Teacher getTeacher(int teacherId) {
        log.info("Teacher ID {} retrieved", teacherId);
        return new Teacher();
    }

    public boolean insertTeacher(Teacher teacher) {
        log.info("Teacher ID {} inserted", teacher.getId());
        return true;
    }

    public boolean updateTeacher(Teacher teacher) {
        log.info("Teacher ID {} updated", teacher.getId());
        return true;
    }

    public boolean deleteTeacher(Teacher teacher) {
        log.info("Teacher ID {} deleted", teacher.getId());
        return true;
    }

}