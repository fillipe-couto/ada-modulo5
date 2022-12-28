package com.ada.modulo5.school_api.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.ada.modulo5.school_api.dto.StudentDtoRequest;
import com.ada.modulo5.school_api.dto.StudentDtoResponse;
import com.ada.modulo5.school_api.mapper.StudentMapper;
import com.ada.modulo5.school_api.model.Student;
import com.ada.modulo5.school_api.model.Teacher;
import com.ada.modulo5.school_api.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    private final StudentMapper mapper;

    public List<StudentDtoResponse> listStudents() {
        return mapper.toListResponse(repository.listAll());
    }

    public StudentDtoResponse getStudent(int studentId) {
        return mapper.toResponse(repository.findById(studentId));
    }

    @Transactional
    public StudentDtoResponse insertStudent(StudentDtoRequest request) {
        Student student = mapper.toNewEntity(request);
        repository.persistAndFlush(student);
        return mapper.toResponse(student);
    }

    @Transactional
    public StudentDtoResponse updateStudent(int studentId, StudentDtoRequest request) {
        Student student = repository.findById(studentId);
        if (student == null) {
            throw new NotFoundException(String.format("Estudante de ID %d não encontrado", studentId));
        }
        repository.persistAndFlush(mapper.updateEntity(request, student));
        return mapper.toResponse(student);
    }

    @Transactional
    public StudentDtoResponse patchStudentsTutor(int studentId, int tutorId) {
        Student student = repository.findById(studentId);
        if (student == null) {
            throw new NotFoundException(String.format("Estudante de ID %d não encontrado", studentId));
        }
        Teacher tutor = Teacher.findById(tutorId);
        if (tutor == null) {
            throw new NotFoundException(String.format("Tutor de ID %d não encontrado", tutorId));
        }
        student.setTutor(tutor);
        repository.persistAndFlush(student);
        return mapper.toResponse(student);
    }

    // public boolean deleteStudent(Student student) {
    // log.info("Student ID {} deleted", student.getId());
    // return true;
    // }

}