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

import com.ada.modulo5.school_api.model.Course;
import com.ada.modulo5.school_api.service.CourseService;

@Path("/course")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    private final CourseService service;

    public CourseResource(CourseService service) {
        this.service = service;
    }

    @GET
    public Response listCourses() {
        service.listCourses();
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getCourse(@PathParam("id") int courseId) {
        service.getCourse(courseId);
        return Response.ok().build();
    }

    @POST
    public Response insertCourse(Course course) {
        return Response.status(
                service.insertCourse(course) ? Response.Status.CREATED : Response.Status.BAD_REQUEST)
                .build();
    }

    @PUT
    public Response updateCourse(Course course) {
        return Response.status(
                service.updateCourse(course) ? Response.Status.OK : Response.Status.BAD_REQUEST)
                .build();
    }

    @DELETE
    public Response deleteCourse(Course course) {
        return Response.status(
                service.deleteCourse(course) ? Response.Status.NOT_FOUND : Response.Status.BAD_REQUEST)
                .build();
    }

}
