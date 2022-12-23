package com.ada.modulo5.school_api.repository;

import javax.enterprise.context.ApplicationScoped;

import com.ada.modulo5.school_api.model.Course;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<Course, Integer> {
}
