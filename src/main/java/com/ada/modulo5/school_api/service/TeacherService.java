package com.ada.modulo5.school_api.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ada.modulo5.school_api.dto.StudentDtoResponse;
import com.ada.modulo5.school_api.dto.TeacherDtoResponse;
import com.ada.modulo5.school_api.mapper.StudentMapper;
import com.ada.modulo5.school_api.mapper.TeacherMapper;
import com.ada.modulo5.school_api.model.Teacher;

@ApplicationScoped
public class TeacherService {

    @Inject
    TeacherMapper mapper;

    @Inject
    StudentMapper studentMapper;

    public List<Teacher> listTeachers() {
        return Teacher.listAll();
    }

    public TeacherDtoResponse getTeacher(int teacherId) throws Exception {
        if (teacherId <= 0) {
            throw new Exception("ID deve ser maior que 0");
        }
        Teacher response = Teacher.findById(teacherId);
        return mapper.getTeacherResponse(response);
    }

    public List<StudentDtoResponse> getStudentsByTeacher(int teacherId) throws Exception {
        if (teacherId <= 0) {
            throw new Exception("ID deve ser maior que 0");
        }
        Teacher response = Teacher.findById(teacherId);
        return studentMapper.toListResponse(response.getAlunos());
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