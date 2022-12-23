package com.ada.modulo5.school_api.mapper;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.ada.modulo5.school_api.dto.TeacherDtoResponse;
import com.ada.modulo5.school_api.model.Teacher;

@ApplicationScoped
public class TeacherMapper {

    public TeacherDtoResponse getTeacherResponse(Teacher teacher) throws Exception {
        return TeacherDtoResponse.builder().id(teacher.getId()).name(teacher.getName()).build();
    }

}
