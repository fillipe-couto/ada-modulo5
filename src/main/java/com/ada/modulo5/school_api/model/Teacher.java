package com.ada.modulo5.school_api.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "PROFESSORES")
public class Teacher extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private int id;

    @Column(name = "professor_name", nullable = false)
    @NotBlank(message = "Nome de Professor não pode ser em branco")
    @Size(min = 2, message = "É necessário um mínimo de 2 caracteres para nome de Professor")
    private String name;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
    private List<Student> alunos;

    @PrePersist
    public void prePersist() {
        setDateTime(LocalDateTime.now());
    }

}
