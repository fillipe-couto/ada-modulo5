package com.ada.modulo5.school_api.resource;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
        return Response.ok(service.getStudent(studentId)).build();
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

    // @DELETE
    // public Response deleteStudent(Student student) {
    // return Response.status(
    // service.deleteStudent(student) ? Response.Status.NOT_FOUND :
    // Response.Status.BAD_REQUEST)
    // .build();
    // }

}
