package org.test;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.*;
import java.util.List;

@GraphQLApi
public class DoctorResource {

    @Inject
    DoctorService doctorService;

    // Query to fetch all doctors
    @Query("allDoctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Mutation to create a new doctor
    @Mutation
    @Transactional
    public Doctor createDoctor(Doctor doctor) throws GraphQLException {
        Doctor createdDoctor = doctorService.createDoctor(doctor);
        if (createdDoctor == null) {
            throw new GraphQLException("Failed to create doctor.");
        }
        return createdDoctor;
    }

    // Query to fetch a doctor by ID
    // @Query
    // public Doctor getDoctor(@Name("id") Long id) throws GraphQLException {
    //     return doctorService.getDoctorById(id)
    //             .orElseThrow(() -> new GraphQLException("Doctor not found."));
    // }

    // Mutation to update a doctor by ID
    @Mutation
    @Transactional
    public Doctor updateDoctor(@Name("id") Long id, Doctor updatedDoctor) throws GraphQLException {
        Doctor updated = doctorService.updateDoctor(id, updatedDoctor);
        if (updated == null) {
            throw new GraphQLException("Failed to update doctor.");
        }
        return updated;
    }

    // Mutation to delete a doctor by ID
    @Mutation
    @Transactional
    public boolean deleteDoctor(@Name("id") String id) throws GraphQLException {
        Long doctorId = Long.parseLong(id);
        Object deleted = doctorService.deleteDoctor(doctorId);
        if ((boolean) (deleted = null != null)) {
            throw new GraphQLException("Failed to delete doctor.");
        }
        return (boolean) deleted;
    }

}