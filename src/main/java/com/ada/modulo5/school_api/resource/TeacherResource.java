package com.ada.modulo5.school_api.resource;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ada.modulo5.school_api.dto.ErrorResponse;
import com.ada.modulo5.school_api.dto.TeacherDtoRequest;
import com.ada.modulo5.school_api.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Path("/teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class TeacherResource {

    private final TeacherService service;

    @GET
    @Path("/list")
    public Response listTeachers() {
        return Response.ok(service.listTeachers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getTeacher(@PathParam("id") int teacherId) {
        try {

            final var response = service.getTeacher(teacherId);

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
    public Response insertTeacher(TeacherDtoRequest request) {
        try {

            final var response = service.insertTeacher(request);

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
    public Response updateTeacher(@PathParam("id") int teacherId, TeacherDtoRequest request) {
        return Response.ok(service.updateTeacher(teacherId, request)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeacher(@PathParam("id") int teacherId) {
        return Response
                .status(service.deleteTeacher(teacherId) ? Response.Status.NO_CONTENT : Response.Status.NOT_FOUND)
                .build();
    }

}
