package com.ada.modulo5.school_api.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.ada.modulo5.school_api.dto.TeacherDtoRequest;
import com.ada.modulo5.school_api.dto.TeacherDtoResponse;
import com.ada.modulo5.school_api.mapper.TeacherMapper;
import com.ada.modulo5.school_api.model.Teacher;

import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherMapper mapper;

    public List<TeacherDtoResponse> listTeachers() {
        return mapper.toListResponse(Teacher.listAll());
    }

    public TeacherDtoResponse getTeacher(int teacherId) {
        Teacher teacher = Teacher.findById(teacherId);
        if (teacher == null) {
            throw new EntityNotFoundException(String.format("Professor de ID %d não encontrado", teacherId));
        }
        return mapper.toResponse(Teacher.findById(teacherId));
    }

    @Transactional
    public TeacherDtoResponse insertTeacher(TeacherDtoRequest request) {
        Teacher teacher = mapper.toNewEntity(request);
        teacher.persistAndFlush();
        return mapper.toResponse(teacher);
    }

    @Transactional
    public TeacherDtoResponse updateTeacher(int teacherId, TeacherDtoRequest request) {
        Teacher teacher = Teacher.findById(teacherId);
        if (teacher == null) {
            throw new NotFoundException(String.format("Estudante de ID %d não encontrado", teacherId));
        }
        teacher.setName(request.getName());
        teacher.persistAndFlush();
        return mapper.toResponse(teacher);
    }

    @Transactional
    public Boolean deleteTeacher(int teacherId) {
        return Teacher.deleteById(teacherId);
    }

}
