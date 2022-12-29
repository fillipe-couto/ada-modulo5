package com.ada.modulo5.school_api.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.ada.modulo5.school_api.dto.TeacherDtoRequest;
import com.ada.modulo5.school_api.dto.TeacherDtoResponse;
import com.ada.modulo5.school_api.model.Teacher;

@RequestScoped
public class TeacherMapper {

    public TeacherDtoResponse toResponse(Teacher teacher) {
        return TeacherDtoResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .build();
    }

    public List<TeacherDtoResponse> toListResponse(List<Teacher> response) {
        List<TeacherDtoResponse> teachersResponse = new ArrayList<TeacherDtoResponse>();
        response.stream().forEach(a -> teachersResponse.add(toResponse(a)));
        return teachersResponse;
    }

    public Teacher toNewEntity(TeacherDtoRequest request) {
        return Teacher.builder().name(request.getName()).build();
    }

}
