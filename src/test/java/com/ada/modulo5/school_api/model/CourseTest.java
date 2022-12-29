package com.ada.modulo5.school_api.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import javax.persistence.Id;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nl.jqno.equalsverifier.EqualsVerifier;

class CourseTest {
    private static final Integer ID = 100;
    private static final String NOME = "Nome de Teste";
    private static final String DESCRICAO = "Descrição de Teste";
    private static final Integer DURACAO = 25;

    @Test
    void constructorBuilder() {

        // GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        // WHEN
        var entity = Course.builder()
                .id(ID)
                .nome(NOME)
                .descricao(DESCRICAO)
                .duracao(DURACAO)
                .build();

        // THEN
        assertFields(validator, entity);
        factory.close();
    }

    @Test
    void constructorAllArgs() {
        // GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        // WHEN
        var entity = new Course();
        entity.setId(ID);
        entity.setNome(NOME);
        entity.setDescricao(DESCRICAO);
        entity.setDuracao(DURACAO);

        // THEN
        assertFields(validator, entity);
        factory.close();
    }

    @Test
    void constructorDefault() {
        // GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        // WHEN
        var entity = new Course();
        entity.setId(ID);
        entity.setNome(NOME);
        entity.setDescricao(DESCRICAO);
        entity.setDuracao(DURACAO);

        // THEN
        assertFields(validator, entity);
        factory.close();
    }

    @ParameterizedTest
    @MethodSource("invalidFields")
    void constructor_NotAllowed(final String name, final String errorMessage) {
        // GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        // WHEN
        var entity = Course.builder().id(ID).nome(NOME).descricao(DESCRICAO).duracao(5).build();

        // THEN
        final var violations = validator.validate(entity);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage())
                .isEqualTo(errorMessage);
        factory.close();
    }

    // @Test
    // void equalsAndHashcodeContract() {
    // EqualsVerifier.simple().forClass(Course.class)
    // .withIgnoredAnnotations(Id.class)
    // .withPrefabValues(Course.class,
    // Course.builder().nome("C1").titular(new
    // Teacher()).descricao(DESCRICAO).duracao(DURACAO)
    // .build(),
    // new Course())
    // .verify();
    // }

    static Stream<Arguments> invalidFields() {
        return Stream.of(
                arguments(null, "Duração de Curso deve ser no mínimo 12 horas"));
    }

    private void assertFields(final Validator validator, final Course entity) {
        final var violations = validator.validate(entity);
        assertThat(violations).isEmpty();

        assertThat(entity.getNome()).isEqualTo(NOME);
    }
}
