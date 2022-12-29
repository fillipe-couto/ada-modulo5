package com.ada.modulo5.school_api.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.ada.modulo5.school_api.dto.CourseDtoRequest;
import com.ada.modulo5.school_api.dto.CourseDtoResponse;
import com.ada.modulo5.school_api.model.Course;

@RequestScoped
public class CourseMapper {

    public CourseDtoResponse toResponse(Course course) {
        return CourseDtoResponse.builder()
                .id(course.getId())
                .nome(course.getNome())
                .descricao(course.getDescricao())
                .duracao(course.getDuracao())
                .titular(course.getTitular() == null ? null : course.getTitular().getName())
                .build();
    }

    public List<CourseDtoResponse> toListResponse(List<Course> response) {
        List<CourseDtoResponse> coursesResponse = new ArrayList<CourseDtoResponse>();
        response.stream().forEach(a -> coursesResponse.add(toResponse(a)));
        return coursesResponse;
    }

    public Course toNewEntity(CourseDtoRequest request) {
        return Course.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .duracao(request.getDuracao())
                .build();
    }

    public Course updateEntity(CourseDtoRequest request, Course entity) {
        entity.setNome(request.getNome());
        entity.setDescricao(request.getDescricao());
        entity.setDuracao(request.getDuracao());
        entity.setTitular(request.getTitular());
        return entity;
    }

}
