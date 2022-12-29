package com.ada.modulo5.school_api.resource;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ada.modulo5.school_api.dto.ErrorResponse;
import com.ada.modulo5.school_api.dto.StudentDtoRequest;
import com.ada.modulo5.school_api.service.StudentService;

import lombok.RequiredArgsConstructor;

@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService service;

    @GET
    @Path("/list")
    public Response listStudents() {
        return Response.ok(service.listStudents()).build();
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") int studentId) {
        try {

            final var response = service.getStudent(studentId);

            return Response
                    .status(Response.Status.FOUND)
                    .entity(response)
                    .build();

        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.createFromEntity(e))
                    .build();
        }
    }

    @POST
    public Response insertStudent(StudentDtoRequest request) {
        try {

            final var response = service.insertStudent(request);

            return Response
                    .status(Response.Status.CREATED)
                    .entity(response)
                    .build();

        } catch (ConstraintViolationException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.createFromValidation(e))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") int studentId, StudentDtoRequest request) {
        return Response.ok(service.updateStudent(studentId, request)).build();
    }

    @PATCH
    @Path("/{studentId}/tutor/{tutorId}")
    public Response patchStudentsTutor(
            @PathParam("studentId") int studentId,
            @PathParam("tutorId") int tutorId) {
        return Response.ok(service.patchStudentsTutor(studentId, tutorId)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") int studentId) {
        return Response
                .status(service.deleteStudent(studentId) ? Response.Status.NO_CONTENT : Response.Status.NOT_FOUND)
                .build();
    }

}
