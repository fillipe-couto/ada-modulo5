package com.ada.modulo5.school_api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.persistence.Id;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StudentTest {
    private static final String NOME = "Nome de Teste";
    private static final Integer ID = 100;
    private static final Teacher TUTOR = Teacher.builder().id(ID).name(NOME).build();
    private static final LocalDateTime DATETIME = LocalDateTime.now().plusDays(-1);

    @Test
    void constructorBuilder() {

        // GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        // WHEN
        var entity = Student.builder()
                .id(ID)
                .nome(NOME)
                .tutor(TUTOR)
                .dateTime(DATETIME)
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
        var entity = new Student();
        entity.setId(ID);
        entity.setNome(NOME);
        entity.setTutor(TUTOR);

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
        var entity = new Student();
        entity.setId(ID);
        entity.setNome(NOME);
        entity.setTutor(TUTOR);
        entity.setDateTime(DATETIME);

        // THEN
        assertFields(validator, entity);
        factory.close();
    }

    @Test
    void prePersist() {
        // GIVEN
        var entity = Student.builder().id(ID).nome(NOME).tutor(TUTOR).build();

        // WHEN
        entity.prePersist();

        // THEN
        assertThat(entity.getDateTime()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("invalidFields")
    void constructor_NotAllowed(final String name, final String errorMessage) {
        // GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        // WHEN
        var entity = Student.builder().id(ID).nome(null).tutor(TUTOR).build();

        // THEN
        final var violations = validator.validate(entity);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage())
                .isEqualTo(errorMessage);
        factory.close();
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(Student.class)
                .withIgnoredAnnotations(Id.class)
                .withPrefabValues(Teacher.class,
                        Teacher.builder().name("T1").build(), new Teacher())
                .verify();
    }

    static Stream<Arguments> invalidFields() {
        return Stream.of(
                arguments(null, "Nome de Aluno não pode ser em branco"),
                arguments("   ", "Nome de Aluno não pode ser em branco"));
    }

    private void assertFields(final Validator validator, final Student entity) {
        final var violations = validator.validate(entity);
        assertThat(violations).isEmpty();

        assertThat(entity.getNome()).isEqualTo(NOME);
    }
}
