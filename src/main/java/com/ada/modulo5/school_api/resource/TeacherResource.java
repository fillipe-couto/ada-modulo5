package com.ada.modulo5.school_api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ada.modulo5.school_api.service.TeacherService;

@Path("/teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherResource {

    private final TeacherService service;

    public TeacherResource(TeacherService service) {
        this.service = service;
    }

    @GET
    @Path("/listAll")
    public Response listTeachers() {
        return Response.ok(service.listTeachers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getTeacher(@PathParam("id") int teacherId) throws Exception {
        return Response.ok(service.getTeacher(teacherId)).build();
    }

    @GET
    @Path("/{id}/students")
    public Response getStudentsByTeacher(@PathParam("id") int teacherId) throws Exception {
        return Response.ok(service.getStudentsByTeacher(teacherId)).build();
    }

    // @POST
    // public Response insertTeacher(TeacherRequest teacher) {
    // return Response.status(
    // service.insertTeacher(teacher) ? Response.Status.CREATED :
    // Response.Status.BAD_REQUEST)
    // .build();
    // }

    // @PUT
    // public Response updateTeacher(Teacher teacher) {
    // return Response.status(
    // service.updateTeacher(teacher) ? Response.Status.OK :
    // Response.Status.BAD_REQUEST)
    // .build();
    // }

    // @DELETE
    // public Response deleteTeacher(Teacher teacher) {
    // return Response.status(
    // service.deleteTeacher(teacher) ? Response.Status.NOT_FOUND :
    // Response.Status.BAD_REQUEST)
    // .build();
    // }

}
