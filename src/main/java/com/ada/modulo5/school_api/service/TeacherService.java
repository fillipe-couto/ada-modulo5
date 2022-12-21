package com.ada.modulo5.school_api.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ada.modulo5.school_api.dto.TeacherResponse;
import com.ada.modulo5.school_api.mapper.TeacherMapper;
import com.ada.modulo5.school_api.model.Teacher;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class TeacherService {

    @Inject
    TeacherMapper mapper;

    public List<Teacher> listTeachers() {
        return Teacher.listAll();
    }

    public TeacherResponse getTeacher(int teacherId) throws Exception {
        if (teacherId <= 0) {
            throw new Exception("ID deve ser maior que 0");
        }
        Teacher response = Teacher.findById(teacherId);
        return mapper.getTeacherResponse(response);
    }

    // public TeacherResponse insertTeacher(Teacher teacher) {
    // T
    // }

    // public boolean updateTeacher(Teacher teacher) {
    // log.info("Teacher ID {} updated", teacher.getId());
    // return true;
    // }

    // public boolean deleteTeacher(Teacher teacher) {
    // log.info("Teacher ID {} deleted", teacher.getId());
    // return true;
    // }

}