package com.ada.modulo5.school_api.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ErrorResponse {

    private String message;
    private Collection<ErrorMessage> errors;

    public static ErrorResponse createFromValidation(ConstraintViolationException constraintViolationException) {
        var violations = constraintViolationException
                .getConstraintViolations()
                .stream()
                .map(cv -> new ErrorMessage(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());
        return new ErrorResponse("Validation Errors", violations);

    }

    public static ErrorResponse createFromEntity(EntityNotFoundException entityNotFoundException) {
        return new ErrorResponse("Entity Error",
                Arrays.asList(new ErrorMessage("Entity", entityNotFoundException.getMessage())));

    }

}
