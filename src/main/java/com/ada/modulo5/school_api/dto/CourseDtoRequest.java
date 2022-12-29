package com.ada.modulo5.school_api.dto;

import com.ada.modulo5.school_api.model.Teacher;
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
public class CourseDtoRequest {
    private String nome;
    private String descricao;
    private int duracao;
    private Teacher titular;
}
