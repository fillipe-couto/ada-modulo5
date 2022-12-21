package com.ada.modulo5.school_api.repository;

import javax.enterprise.context.ApplicationScoped;

import com.ada.modulo5.school_api.model.Student;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<Student, Integer> {
}
