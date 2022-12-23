package com.ada.modulo5.school_api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURSOS")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private int id;

    @Column(name = "curso_name", nullable = false)
    @NotBlank(message = "Nome de Curso não pode ser em branco")
    @Size(min = 5, message = "É necessário um mínimo de 5 caracteres para nome de Curso")
    private String nome;

    @Column(name = "curso_description", nullable = false)
    @NotBlank(message = "Descrição de Curso não pode ser em branco")
    @Size(min = 10, message = "É necessário um mínimo de 10 caracteres para descrição de Curso")
    private String descricao;

    @Column(name = "curso_duration", nullable = false)
    @NotBlank(message = "Duração de Curso não pode ser em branco")
    @Min(value = 12, message = "Duração de Curso deve ser no mínimo 12 horas")
    private int duracao;

}
