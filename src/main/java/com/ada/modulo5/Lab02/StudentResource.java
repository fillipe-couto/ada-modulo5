package com.ada.modulo5.Lab02;

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

import com.ada.modulo5.Lab02.DTO.StudentDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {

    @GET
    public Response listStudents() {
        log.info("All Students retrieved!");
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") int studentId) {
        log.info("Student ID {} retrieved", studentId);
        return Response.ok().build();
    }

    @POST
    public Response insertStudent(StudentDTO student) {
        log.info("Student ID {} inserted", student.getId());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateStudent(StudentDTO student) {
        log.info("Student ID {} updated", student.getId());
        return Response.status(Response.Status.FOUND).build();
    }

    @DELETE
    public Response deleteStudent(StudentDTO student) {
        log.info("Student ID {} deleted", student.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}