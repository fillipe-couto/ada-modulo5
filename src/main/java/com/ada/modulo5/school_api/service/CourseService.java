package com.ada.modulo5.school_api.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.ada.modulo5.school_api.dto.CourseDtoRequest;
import com.ada.modulo5.school_api.dto.CourseDtoResponse;
import com.ada.modulo5.school_api.mapper.CourseMapper;
import com.ada.modulo5.school_api.model.Course;
import com.ada.modulo5.school_api.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    private final CourseMapper mapper;

    public List<CourseDtoResponse> listCourses() {
        return mapper.toListResponse(repository.listAll());
    }

    public CourseDtoResponse getCourse(int courseId) {
        Course course = repository.findById(courseId);
        if (course == null) {
            throw new EntityNotFoundException(String.format("Curso de ID %d não encontrado", courseId));
        }
        return mapper.toResponse(repository.findById(courseId));
    }

    @Transactional
    public CourseDtoResponse insertCourse(CourseDtoRequest request) {
        Course course = mapper.toNewEntity(request);
        repository.persistAndFlush(course);
        return mapper.toResponse(course);
    }

    @Transactional
    public CourseDtoResponse updateCourse(int courseId, CourseDtoRequest request) {
        Course course = repository.findById(courseId);
        if (course == null) {
            throw new NotFoundException(String.format("Curso de ID %d não encontrado", courseId));
        }
        repository.persistAndFlush(mapper.updateEntity(request, course));
        return mapper.toResponse(course);
    }

    @Transactional
    public Boolean deleteCourse(int courseId) {
        return repository.deleteById(courseId);
    }

}