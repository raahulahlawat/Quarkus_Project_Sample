package org.test;

import java.util.List;
import java.util.Optional;

import org.eclipse.microprofile.graphql.Query;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/graphql")
public class GraphQLResource {

    @Inject
    DoctorService doctorService;

    @Query("allDoctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @Query("getDoctorById")
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        return doctor.orElse(null);
    }
}
