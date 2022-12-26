package com.ada.modulo5.school_api.dto;

import java.util.List;

import com.ada.modulo5.school_api.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class TeacherDtoResponse {
    private int id;
    private String name;
}
