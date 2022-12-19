package com.ada.modulo5.school_api.resource;

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

import com.ada.modulo5.school_api.model.Student;
import com.ada.modulo5.school_api.service.StudentService;

@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {

    private final StudentService service;

    public StudentResource(StudentService service) {
        this.service = service;
    }

    @GET
    public Response listStudents() {
        service.listStudents();
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") int studentId) {
        service.getStudent(studentId);
        return Response.ok().build();
    }

    @POST
    public Response insertStudent(Student student) {
        return Response.status(
                service.insertStudent(student) ? Response.Status.CREATED : Response.Status.BAD_REQUEST)
                .build();
    }

    @PUT
    public Response updateStudent(Student student) {
        return Response.status(
                service.updateStudent(student) ? Response.Status.OK : Response.Status.BAD_REQUEST)
                .build();
    }

    @DELETE
    public Response deleteStudent(Student student) {
        return Response.status(
                service.deleteStudent(student) ? Response.Status.NOT_FOUND : Response.Status.BAD_REQUEST)
                .build();
    }

}
