// package org.test;

// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.core.MediaType;

// @Path("/hello")
// public class GreetingResource {

//     @GET
//     @Produces(MediaType.TEXT_PLAIN)
//     public String hello() {
//         return "Hello Vinay";
//     }
// }








package org.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

@Path("/greeting")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    private final List<Doctor> doctors = new ArrayList<>();

    // Get all doctors
    @GET
    @Path("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    // Create a new doctor
    // @POST
    // @Path("/doctors")
    // public Response createDoctor(Doctor doctor) {
    //     doctors.add(doctor);
    //     return Response.status(Response.Status.CREATED).entity(doctor).build();
    // }

    // Create a new doctor
    @POST
    @Path("/doctors")
    public String createDoctor(Doctor doctor) {
        doctors.add(doctor);
        return "Doctor created: " + doctor.getName();
    }

    // // Update an existing doctor
    // @PUT
    // @Path("/doctors/{id}")
    // public Response updateDoctor(@PathParam("id") Long id, Doctor updatedDoctor) {
    //     Optional<Doctor> existingDoctor = doctors.stream()
    //             .filter(d -> d.getId().equals(id))
    //             .findFirst();

    //     if (existingDoctor.isPresent()) {
    //         Doctor doctor = existingDoctor.get();
    //         doctor.setName(updatedDoctor.getName());
    //         doctor.setSpecialty(updatedDoctor.getSpecialty());
    //         return Response.ok(doctor).build();
    //     } else {
    //         return Response.status(Response.Status.NOT_FOUND).build();
    //     }
    // }

    // // Delete a doctor
    // @DELETE
    // @Path("/doctors/{id}")
    // public Response deleteDoctor(@PathParam("id") Long id) {
    //     Optional<Doctor> existingDoctor = doctors.stream()
    //             .filter(d -> d.getId().equals(id))
    //             .findFirst();

    //     if (existingDoctor.isPresent()) {
    //         doctors.remove(existingDoctor.get());
    //         return Response.ok().build();
    //     } else {
    //         return Response.status(Response.Status.NOT_FOUND).build();
    //     }

    // Update an existing doctor
    @PUT
    @Path("/doctors/{id}")
    public String updateDoctor(@PathParam("id") Long id, Doctor updatedDoctor) {
        Optional<Doctor> existingDoctor = doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();

        if (existingDoctor.isPresent()) {
            Doctor doctor = existingDoctor.get();
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialty(updatedDoctor.getSpecialty());
            return "Doctor updated: " + doctor.getName();
        } else {
            return "Doctor not found";
        }
    }

    // Delete a doctor
    @DELETE
    @Path("/doctors/{id}")
    public String deleteDoctor(@PathParam("id") Long id) {
        Optional<Doctor> existingDoctor = doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();

        if (existingDoctor.isPresent()) {
            doctors.remove(existingDoctor.get());
            return "Doctor deleted";
        } else {
            return "Doctor not found";
        }
    }
    

    // Delete doctors with null values
    // @DELETE
    // @Path("/doctors/nulls")
    // public Response deleteNullDoctors() {
    //     Iterator<Doctor> iterator = doctors.iterator();
    //     while (iterator.hasNext()) {
    //         Doctor doctor = iterator.next();
    //         if (doctor.getId() == null || doctor.getName() == null || doctor.getSpecialty() == null) {
    //             iterator.remove();
    //         }
    //     }
    //     return Response.ok().build();
    // }
}












