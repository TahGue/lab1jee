package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService StudentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        StudentService.createStudent(student);
        return Response.ok(student).build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        StudentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) {
        Student foundStudent = StudentService.findStudentById(id);

        if (foundStudent == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " was not found in database.").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundStudent).build();
    }

    @Path("query")
    @GET
    public Response getAllStudent(@QueryParam("student") String student) {

        // Här ska logik finnas tillgänglig som filtrerar ut alla student efter vald kategori
        // Lämpligtvis finns logiken i Service-klassen och metoden anropas härifrån

        String responseString = "Här skall en lista presenteras som innehåller alla student i kategori " + student;
        return Response.ok(responseString).type(MediaType.TEXT_PLAIN_TYPE).build();


    }


    @Path("")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudent = StudentService.getAllStudents();
        return Response.ok(foundStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        StudentService.deleteStudent(id);
        return Response.ok().build();
    }

    @Path("{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, Student student) {


        Student updatedStudent = StudentService.updatePhoneNumber(id, student.getPhoneNumber());
        return Response.ok(updatedStudent).build();
    }




}
