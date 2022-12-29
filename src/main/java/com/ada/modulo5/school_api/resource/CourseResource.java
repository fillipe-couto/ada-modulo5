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

import com.ada.modulo5.school_api.dto.CourseDtoRequest;
import com.ada.modulo5.school_api.dto.ErrorResponse;
import com.ada.modulo5.school_api.service.CourseService;

import lombok.RequiredArgsConstructor;

@Path("/course")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class CourseResource {

    private final CourseService service;

    @GET
    @Path("/list")
    public Response listCourses() {
        return Response.ok(service.listCourses()).build();
    }

    @GET
    @Path("/{id}")
    public Response getCourse(@PathParam("id") int courseId) {
        try {

            final var response = service.getCourse(courseId);

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
    public Response insertCourse(CourseDtoRequest request) {
        try {

            final var response = service.insertCourse(request);

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
    public Response updateCourse(@PathParam("id") int courseId, CourseDtoRequest request) {
        return Response.ok(service.updateCourse(courseId, request)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCourse(@PathParam("id") int courseId) {
        return Response
                .status(service.deleteCourse(courseId) ? Response.Status.NO_CONTENT : Response.Status.NOT_FOUND)
                .build();
    }

}
