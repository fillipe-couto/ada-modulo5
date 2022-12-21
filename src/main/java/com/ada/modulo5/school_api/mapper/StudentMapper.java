package com.ada.modulo5.school_api.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.ada.modulo5.school_api.dto.StudentDtoRequest;
import com.ada.modulo5.school_api.dto.StudentDtoResponse;
import com.ada.modulo5.school_api.model.Student;

@RequestScoped
public class StudentMapper {

    public StudentDtoResponse toResponse(Student student) {
        return StudentDtoResponse.builder()
                .id(student.getId())
                .nome(student.getNome())
                .build();
    }

    public List<StudentDtoResponse> toListResponse(List<Student> response) {
        List<StudentDtoResponse> studentsResponse = new ArrayList<StudentDtoResponse>();
        response.stream().forEach(a -> studentsResponse.add(toResponse(a)));
        return studentsResponse;
    }

    public Student toEntity(StudentDtoRequest request) {
        return Student.builder().nome(request.getNome()).build();
    }

    public Student updateEntity(StudentDtoRequest request, Student entity) {
        entity.setNome(request.getNome());
        return entity;
    }

}
