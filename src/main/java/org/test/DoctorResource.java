package org.test;

import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.*;
import java.util.List;

@GraphQLApi
public class DoctorResource {

    private final DoctorService doctorService;

    // Constructor injection
    public DoctorResource(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Query to fetch all doctors with pagination
    @Query("allDoctors")
    public List<Doctor> getAllDoctors(@Name("pageNumber") int pageNumber, @Name("pageSize") int pageSize) {
        return doctorService.getAllDoctors(pageNumber, pageSize);
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
    public boolean deleteDoctor(@Name("id") Long id) throws GraphQLException {
        boolean deleted = doctorService.deleteDoctor(id);
        if (!deleted) {
            throw new GraphQLException("Failed to delete doctor.");
        }
        return deleted;
    }
}
